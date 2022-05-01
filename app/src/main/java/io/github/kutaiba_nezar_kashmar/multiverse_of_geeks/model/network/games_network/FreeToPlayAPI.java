package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.games_network;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FreeToPlayAPI
{
  @GET("games")
  Call<ArrayList<AllFreeToPlayGamesResponse>> getLiveFreeToPlay();

  @GET("game")
  Call<FreeToPlayGameResponse> getFreeToPlayGameById(@Query("id") int id);
}
