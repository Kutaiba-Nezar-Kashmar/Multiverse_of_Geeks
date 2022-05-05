package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.reactivex.rxjava3.core.Flowable;

public interface FreeToPlayGamesRepository
{
  MutableLiveData<FreeToPlayGameResponse> findFreeToPlayGame(int id);
  MutableLiveData<List<AllFreeToPlayGamesResponse>> getAllFreeToPlayGames();
}
