package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.Tv_show_network;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.CommentResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.TvShowResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TVShowAPI
{
  @GET("tv/{tv_id}")
  Call<TvShowResponse> getTvShowById(@Path("tv_id") int tv_id,
      @Query("api_key") String apiKey);
  @GET("tv/popular")
  Call<TvShowResponse> getAllPopularTvShows(@Query("api_key") String apiKey);
  @GET("tv/top_rated")
  Call<TvShowResponse> getAllTopRatedTvShows(@Query("api_key") String apiKey);
  @GET("tv/on_the_air")
  Call<TvShowResponse> getAllOnAirTvShows(@Query("api_key") String apiKey);
  @GET("tv/airing_today")
  Call<TvShowResponse> getAllAiringTodayTvShows(
      @Query("api_key") String apiKey);
  @GET("search/tv")
  Call<TvShowResponse> searchForTvShow(@Query("api_key") String apiKey,
      @Query("query") String query);
  @GET("tv/{tv_id}/reviews")
  Call<CommentResponse> getTvReviews(@Path("tv_id") int tvId,
      @Query("tv_id") String apiKey);
}
