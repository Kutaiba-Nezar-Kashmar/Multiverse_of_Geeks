package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.FreeToPlayGamesRepository;

public class GamesViewModel extends AndroidViewModel
{
  private final FreeToPlayGamesRepository freeToPlayGamesRepository;

  public GamesViewModel(@NonNull Application application)
  {
    super(application);
    freeToPlayGamesRepository = FreeToPlayGamesRepository.getInstance();
  }

  public LiveData<ArrayList<AllFreeToPlayGamesResponse>> getAllFreeToPlay()
  {
    return freeToPlayGamesRepository.getAllFreeToPlayGames();
  }

  public LiveData<FreeToPlayGameResponse> findFreeToPlayGameById(int id)
  {
    return freeToPlayGamesRepository.findFreeToPlayGame(id);
  }
}
