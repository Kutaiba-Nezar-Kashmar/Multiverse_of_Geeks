package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.games_network;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GamesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GamesAPI
{
  @GET("games")
  Call<GamesResponse> getAllGames(@Query("key") String apiKey);

  @GET("games/{id}")
  Call<GamesResponse> getGameById(@Path("id") int id,
      @Query("key") String apiKey);

  @GET("games")
  Call<GamesResponse> getSearchGames(@Query("key") String apiKey,
      @Query("search") String query);
}
