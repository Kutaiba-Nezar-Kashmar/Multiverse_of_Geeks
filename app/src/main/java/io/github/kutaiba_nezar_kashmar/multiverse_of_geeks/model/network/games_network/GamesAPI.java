package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.games_network;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GamesResponse;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GamesAPI
{
  @GET("games")
  Flowable<GamesResponse> getAllGames(@Query("key") String apiKey);

  @GET("games/{id}")
  Flowable<GamesResponse> getGameById(@Path("id") int id,
      @Query("key") String apiKey);

  @GET("games")
  Flowable<GamesResponse> getSearchGames(@Query("key") String apiKey,
      @Query("search") String query);
}
