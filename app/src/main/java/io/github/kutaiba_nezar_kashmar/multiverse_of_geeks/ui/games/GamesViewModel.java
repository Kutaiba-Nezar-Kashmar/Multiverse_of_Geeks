package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.FreeToPlayGamesRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.FreeToPlayGamesRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.GamesRepository;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GamesViewModel extends AndroidViewModel
{
  private final FreeToPlayGamesRepository freeToPlayGamesRepository;
  private final GamesRepository gamesRepository;
  private MutableLiveData<ArrayList<AllFreeToPlayGamesResponse>> allFreeGames;
  private MutableLiveData<FreeToPlayGameResponse> freeGame;

  public GamesViewModel(@NonNull Application application)
  {
    super(application);
    freeToPlayGamesRepository = new FreeToPlayGamesRepositoryImpl();
    gamesRepository = GamesRepository.getInstance(application);
    allFreeGames = new MutableLiveData<>();
    freeGame = new MutableLiveData<>();
  }

  public LiveData<List<Game>> getFavoriteGames()
  {
    return gamesRepository.getFavoriteGames();
  }

  public LiveData<Game> getSingleFavoriteGame(int id)
  {
    return gamesRepository.getSingleFavoriteGame(id);
  }

  public void insertFavoriteGame(Game game)
  {
    gamesRepository.insertFavoriteGame(game);
  }

  public void deleteFavoriteGame(Game game)
  {
    gamesRepository.deleteFavoriteGame(game);
  }

  public LiveData<ArrayList<AllFreeToPlayGamesResponse>> getAllFreeToPlay()
  {
    freeToPlayGamesRepository.getAllFreeToPlayGames()
        .subscribeOn(Schedulers.io()).doOnNext(allFreeToPlayGamesResponses -> {
      allFreeGames.postValue(allFreeToPlayGamesResponses);
    }).subscribe();
    return allFreeGames;
  }

  public LiveData<FreeToPlayGameResponse> findFreeToPlayGameById(int id)
  {
    freeToPlayGamesRepository.findFreeToPlayGame(id)
        .subscribeOn(Schedulers.io()).doOnNext(freeToPlayGameResponse -> {
      freeGame.postValue(freeToPlayGameResponse);
    }).subscribe();
    return freeGame;
  }

  public LiveData<ArrayList<Game>> getAllGames()
  {
    return gamesRepository.getAllGames();
  }

  public LiveData<Game> getGameById(int id)
  {
    return gamesRepository.getGameById(id);
  }

  public LiveData<ArrayList<Game>> getSearchedGames(String query)
  {
    return gamesRepository.getSearchedGames(query);
  }
}
