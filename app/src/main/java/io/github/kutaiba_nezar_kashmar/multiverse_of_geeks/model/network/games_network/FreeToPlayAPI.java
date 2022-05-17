package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.games_network;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FreeToPlayAPI
{
  /**
   * Retrofit method that returns a response from a webserver. Where the method
   * query a list of AllFreeToPlayGameResponse object
   *
   * @return Retrofit call with the value of a list of AllFreeToPlayGamesResponse
   */
  @GET("games")
  Call<List<AllFreeToPlayGamesResponse>> getLiveFreeToPlay();

  /**
   * Retrofit method that returns a response from a webserver. Where the method
   * query an AllFreeToPlayGameResponse object
   *
   * @param id The game id
   * @return Retrofit call with the value of AllFreeToPlayGamesResponse for the queried game
   */
  @GET("game")
  Call<FreeToPlayGameResponse> getFreeToPlayGameById(@Query("id") int id);
}
