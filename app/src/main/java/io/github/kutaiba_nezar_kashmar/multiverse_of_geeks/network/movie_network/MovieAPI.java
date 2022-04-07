package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.movie_network;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.CommentResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI
{
  @GET("movie/{movie_id}")
  Call<MovieResponse> getMovieById(@Path("movie_id") int movie_id,
      @Query("api_key") String apiKey);
  @GET("movie/popular")
  Call<MovieResponse> getAllPopularMovies(@Query("api_key") String apiKey);
  @GET("movie/top_rated")
  Call<MovieResponse> getAllTopRatedMovies(@Query("api_key") String apiKey);
  @GET("movie/now_playing")
  Call<MovieResponse> getAllNowPlayingMovies(@Query("api_key") String apiKey);
  @GET("movie/upcoming")
  Call<MovieResponse> getAllUpComingsMovies(@Query("api_key") String apiKey);
  @GET("search/movie")
  Call<MovieResponse> searchForMovie(@Query("api_key") String apiKey,
      @Query("query") String query);
  @GET("movie/{movie_id}/reviews")
  Call<CommentResponse> getMovieReviews(@Path("movie_id") int movie_id,
      @Query("api_key") String apiKey);
}
