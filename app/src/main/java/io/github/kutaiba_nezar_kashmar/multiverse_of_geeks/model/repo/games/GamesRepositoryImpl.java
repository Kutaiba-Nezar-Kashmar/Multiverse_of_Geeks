package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.GeekDatabase;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.games.GameDAO;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.GamesClient;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.games_network.GamesAPI;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GamesRepositoryImpl implements GamesRepository
{
  private static GamesRepository instance;
  private final ExecutorService executorService;
  private final GameDAO gameDAO;
  private final LiveData<List<Game>> favoriteGames;
  private final LiveData<Game> singleFavoriteGame;
  private final MutableLiveData<Game> gameById;
  private final MutableLiveData<List<Game>> allGames;
  private final MutableLiveData<List<Game>> searchedGames;

  private GamesRepositoryImpl(Application application)
  {
    GeekDatabase database = GeekDatabase.getInstance(application);
    gameDAO = database.gameDAO();
    executorService = Executors.newFixedThreadPool(2);
    favoriteGames = gameDAO.getAllFavoriteGames();
    singleFavoriteGame = new MutableLiveData<>();
    gameById = new MutableLiveData<>();
    allGames = new MutableLiveData<>();
    searchedGames = new MutableLiveData<>();
  }

  public static synchronized GamesRepository getInstance(
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
  public MutableLiveData<List<Game>> getAllGames()
  {
    GamesAPI gamesAPI = GamesClient.gamesAPI();
    Call<GamesResponse> call = gamesAPI.getAllGames(BuildConfig.RAWG_API_KEY);
    call.enqueue(new Callback<GamesResponse>()
    {
      @Override
      public void onResponse(Call<GamesResponse> call,
          Response<GamesResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            allGames.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<GamesResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return allGames;
  }

  @Override
  public MutableLiveData<List<Game>> getSearchedGames(String query)
  {
    GamesAPI gamesAPI = GamesClient.gamesAPI();
    Call<GamesResponse> call = gamesAPI
        .getSearchGames(BuildConfig.RAWG_API_KEY, query);
    call.enqueue(new Callback<GamesResponse>()
    {
      @Override
      public void onResponse(Call<GamesResponse> call,
          Response<GamesResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            searchedGames.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<GamesResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return searchedGames;
  }

  @Override
  public MutableLiveData<Game> getGameById(int id)
  {
    GamesAPI gamesAPI = GamesClient.gamesAPI();
    Call<GamesResponse> call = gamesAPI
        .getGameById(id, BuildConfig.RAWG_API_KEY);
    call.enqueue(new Callback<GamesResponse>()
    {
      @Override
      public void onResponse(Call<GamesResponse> call,
          Response<GamesResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            gameById.setValue(response.body().getGame());
          }
        }
      }

      @Override
      public void onFailure(Call<GamesResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return gameById;
  }
}
