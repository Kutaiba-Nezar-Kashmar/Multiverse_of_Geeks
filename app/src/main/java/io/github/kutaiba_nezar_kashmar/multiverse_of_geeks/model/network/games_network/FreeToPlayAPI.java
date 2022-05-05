package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.games_network;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FreeToPlayAPI
{
  @GET("games")
  Call<List<AllFreeToPlayGamesResponse>> getLiveFreeToPlay();

  @GET("game")
  Call<FreeToPlayGameResponse> getFreeToPlayGameById(@Query("id") int id);
}
