package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.games_network;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.FreeToPlayGameResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FreeToPlayAPI
{
  @GET("games")
  Call<ArrayList<FreeToPlayGameResponse>> getLiveFreeToPlay();
}
