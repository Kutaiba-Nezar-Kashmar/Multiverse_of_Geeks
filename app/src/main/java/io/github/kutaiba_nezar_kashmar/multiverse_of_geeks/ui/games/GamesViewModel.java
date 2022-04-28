package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.games.FreeToPlayGamesRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.games.GamesRepository;

public class GamesViewModel extends AndroidViewModel
{
  private final FreeToPlayGamesRepository freeToPlayGamesRepository;
  private final GamesRepository gamesRepository;

  public GamesViewModel(@NonNull Application application)
  {
    super(application);
    freeToPlayGamesRepository = FreeToPlayGamesRepository.getInstance();
    gamesRepository = GamesRepository.getInstance(application);
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
    return freeToPlayGamesRepository.getAllFreeToPlayGames();
  }

  public LiveData<FreeToPlayGameResponse> findFreeToPlayGameById(int id)
  {
    return freeToPlayGamesRepository.findFreeToPlayGame(id);
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
