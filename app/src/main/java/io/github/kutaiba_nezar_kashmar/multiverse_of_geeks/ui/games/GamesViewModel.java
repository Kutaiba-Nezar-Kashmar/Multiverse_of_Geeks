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
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.GamesRepositoryImpl;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GamesViewModel extends AndroidViewModel
{
  private final FreeToPlayGamesRepository freeToPlayGamesRepository;
  private final GamesRepository gamesRepository;
  private MutableLiveData<ArrayList<AllFreeToPlayGamesResponse>> allFreeGames;
  private MutableLiveData<FreeToPlayGameResponse> freeGame;
  private MutableLiveData<ArrayList<Game>> allGames;
  private MutableLiveData<Game> gameById;
  private MutableLiveData<ArrayList<Game>> searchedGames;

  public GamesViewModel(@NonNull Application application)
  {
    super(application);
    freeToPlayGamesRepository = FreeToPlayGamesRepositoryImpl.getInstance();
    gamesRepository = GamesRepositoryImpl.getInstance(application);
    allFreeGames = new MutableLiveData<>();
    freeGame = new MutableLiveData<>();
    allGames = new MutableLiveData<>();
    gameById = new MutableLiveData<>();
    searchedGames = new MutableLiveData<>();
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
    gamesRepository.getAllGames().subscribeOn(Schedulers.io())
        .doOnNext(gamesResponses -> {
          allGames.postValue(gamesResponses.getResults());
        }).subscribe();
    return allGames;
  }

  public LiveData<Game> getGameById(int id)
  {
    gamesRepository.getGameById(id).subscribeOn(Schedulers.io())
        .doOnNext(game -> {
          gameById.postValue(game);
        }).subscribe();
    return gameById;
  }

  public LiveData<ArrayList<Game>> getSearchedGames(String query)
  {
    gamesRepository.getSearchedGames(query).subscribeOn(Schedulers.io())
        .doOnNext(games -> {
          searchedGames.postValue(games);
        }).subscribe();
    return searchedGames;
  }
}
