package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.free_to_play.FreeToPlayGamesRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.free_to_play.FreeToPlayGamesRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.GamesRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.GamesRepositoryImpl;

public class GamesViewModel extends AndroidViewModel
{
  private final FreeToPlayGamesRepository freeToPlayGamesRepository;
  private final GamesRepository gamesRepository;
  private MutableLiveData<List<AllFreeToPlayGamesResponse>> allFreeGames;
  private MutableLiveData<FreeToPlayGameResponse> freeGame;
  private MutableLiveData<List<Game>> allGames;
  private MutableLiveData<Game> gameById;
  private MutableLiveData<List<Game>> searchedGames;

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

  public LiveData<List<AllFreeToPlayGamesResponse>> getAllFreeToPlay()
  {
    return freeToPlayGamesRepository.getAllFreeToPlayGames();
  }

  public LiveData<FreeToPlayGameResponse> findFreeToPlayGameById(int id)
  {
    return freeToPlayGamesRepository.findFreeToPlayGame(id);
  }

  public LiveData<List<Game>> getAllGames()
  {
    return gamesRepository.getAllGames();
  }

  public LiveData<Game> getGameById(int id)
  {
    return gamesRepository.getGameById(id);
  }

  public LiveData<List<Game>> getSearchedGames(String query)
  {
    return gamesRepository.getSearchedGames(query);
  }
}
