package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.games_network;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GamesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GamesAPI
{
  /**
   * Retrofit method that returns a response from RAWG webserver. Where the method
   * query a GamesResponse object
   *
   * @param apiKey The API key required to authenticate a request
   * @return Retrofit call with the value of a GamesResponse
   */
  @GET("games")
  Call<GamesResponse> getAllGames(@Query("key") String apiKey);

  /**
   * Retrofit method that returns a response from RAWG webserver. Where the method
   * query a GamesResponse object
   *
   * @param id     The game id
   * @param apiKey The API key required to authenticate a request
   * @return Retrofit call with the value of a GamesResponse
   */
  @GET("games/{id}")
  Call<GamesResponse> getGameById(@Path("id") int id,
      @Query("key") String apiKey);

  /**
   * Retrofit method that returns a response from RAWG webserver. Where the method
   * query a GamesResponse object
   *
   * @param apiKey The API key required to authenticate a request
   * @param query  The query parameter to search for a game
   * @return Retrofit call with the value of a GamesResponse
   */
  @GET("games")
  Call<GamesResponse> getSearchGames(@Query("key") String apiKey,
      @Query("search") String query);
}
