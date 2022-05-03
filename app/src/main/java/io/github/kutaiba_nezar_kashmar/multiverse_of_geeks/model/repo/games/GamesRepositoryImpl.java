package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.GeekDatabase;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.games.GameDAO;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.GamesClient;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GamesRepositoryImpl implements GamesRepository
{
  private static GamesRepositoryImpl instance;
  private final ExecutorService executorService;
  private final GameDAO gameDAO;
  private final LiveData<List<Game>> favoriteGames;
  private final LiveData<Game> singleFavoriteGame;

  private GamesRepositoryImpl(Application application)
  {
    GeekDatabase database = GeekDatabase.getInstance(application);
    gameDAO = database.gameDAO();
    executorService = Executors.newFixedThreadPool(2);
    favoriteGames = gameDAO.getAllFavoriteGames();
    singleFavoriteGame = new MutableLiveData<>();
  }

  public static synchronized GamesRepositoryImpl getInstance(
      Application application)
  {
    if (instance == null)
    {
      instance = new GamesRepositoryImpl(application);
    }
    return instance;
  }

  @Override
  public LiveData<List<Game>> getFavoriteGames()
  {
    return favoriteGames;
  }

  @Override
  public LiveData<Game> getSingleFavoriteGame(int id)
  {
    return gameDAO.getGameById(id);
  }

  @Override
  public void insertFavoriteGame(Game game)
  {
    executorService.execute(() -> gameDAO.insertGame(game));
  }

  @Override
  public void deleteFavoriteGame(Game game)
  {
    executorService.execute(() -> gameDAO.deleteGame(game));
  }

  @Override
  public Flowable<GamesResponse> getAllGames()
  {
    return GamesClient.gamesAPI().getAllGames(BuildConfig.RAWG_API_KEY)
        .subscribeOn(Schedulers.io()).flatMap((GamesResponse item) -> {
          return Flowable.just(item);
        });

  }

  @Override
  public Flowable<ArrayList<Game>> getSearchedGames(String query)
  {
    return GamesClient.gamesAPI()
        .getSearchGames(BuildConfig.RAWG_API_KEY, query)
        .subscribeOn(Schedulers.io()).flatMap(item -> {
          return Flowable.just(item.getResults());
        });
  }

  @Override
  public Flowable<Game> getGameById(int id)
  {
    return GamesClient.gamesAPI().getGameById(id, BuildConfig.RAWG_API_KEY)
        .subscribeOn(Schedulers.io()).flatMap(item -> {
          return Flowable.just(item.getGame());
        });
  }
}
