package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.media.Tv_show_network;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.CommentResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.TvShowResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TVShowAPI
{
  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query the details of a chosen tv show
   *
   * @param tv_id  The tv show id to locate the desired tv show
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of TvShowResponse for the queried tv show
   */
  @GET("tv/{tv_id}")
  Call<TvShowResponse> getTvShowById(@Path("tv_id") int tv_id,
      @Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of popular tv shows
   *
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of TvShowResponse for the queried tv shows
   */
  @GET("tv/popular")
  Call<TvShowResponse> getAllPopularTvShows(@Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of top rated tv shows
   *
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of TvShowResponse for the queried tv shows
   */
  @GET("tv/top_rated")
  Call<TvShowResponse> getAllTopRatedTvShows(@Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of on air tv shows
   *
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of TvShowResponse for the queried tv shows
   */
  @GET("tv/on_the_air")
  Call<TvShowResponse> getAllOnAirTvShows(@Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of airing today tv shows
   *
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of TvShowResponse for the queried tv shows
   */
  @GET("tv/airing_today")
  Call<TvShowResponse> getAllAiringTodayTvShows(
      @Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of tv show/tv shows based on the given parameter
   *
   * @param query  The keyword used to search for tv show/tv shows that contain part/entire
   *               part of the query in it's title
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of TvShowResponse for the queried tv show/tv shows
   */
  @GET("search/tv")
  Call<TvShowResponse> searchForTvShow(@Query("api_key") String apiKey,
      @Query("query") String query);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query tv show reviews based on the given parameter
   *
   * @param tvId   The tv show id to locate the desired tv show
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of CommentResponse for the queried tv show
   */
  @GET("tv/{tv_id}/reviews")
  Call<CommentResponse> getTvReviews(@Path("tv_id") int tvId,
      @Query("tv_id") String apiKey);
}
