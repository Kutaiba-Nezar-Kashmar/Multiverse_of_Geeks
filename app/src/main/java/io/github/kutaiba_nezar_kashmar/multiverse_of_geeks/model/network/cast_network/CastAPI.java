package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.cast_network;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.CastResponse;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CastAPI
{
  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query credits (cast&crew) of a selected movie
   *
   * @param movie_id The movie id to locate the desired movie
   * @param apiKey   The api key required to authorize the API call
   * @return Retrofit call with the value of CastResponse for the queried movie
   */
  @GET("movie/{movie_id}/credits")
  Flowable<CastResponse> getMovieCast(@Path("movie_id") int movie_id,
      @Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query credits (cast&crew) of a selected tv show
   *
   * @param tv_id  The Tv show id to locate the desired tv show
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of CastResponse for the queried tv show
   */
  @GET("tv/{tv_id}/credits")
  Flowable<CastResponse> getTvShowCast(@Path("tv_id") int tv_id,
      @Query("api_key") String apiKey);
}
