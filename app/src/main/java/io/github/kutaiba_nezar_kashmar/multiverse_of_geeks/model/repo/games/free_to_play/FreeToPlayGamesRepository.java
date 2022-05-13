package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.free_to_play;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.reactivex.rxjava3.core.Flowable;

public interface FreeToPlayGamesRepository
{
  /**
   * Hold a FreeToPlayGameResponse in A liveData based on given parameter
   *
   * @param id The game id
   * @return MutableLiveData of FreeToPlayGameResponse object
   */
  MutableLiveData<FreeToPlayGameResponse> findFreeToPlayGame(int id);

  /**
   * Hold a list of FreeToPlayGameResponse in A liveData
   *
   * @return MutableLiveData of al list of FreeToPlayGameResponse object
   */
  MutableLiveData<List<AllFreeToPlayGamesResponse>> getAllFreeToPlayGames();
}
