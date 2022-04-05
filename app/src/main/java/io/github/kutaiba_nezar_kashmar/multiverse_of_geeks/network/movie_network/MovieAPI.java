package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.movie_network;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI
{
  //TODO:Rename to get by genre
  //get movie by genre 28: discover/movie?api_key=ee89f342735e902ec9459f6edb50013a&with_genres=28
  @GET("movie/{movie_id}") Call<MovieResponse> getMovies(
      @Path("movie_id") int movie_id, @Query("api_key") String apiKey);
  @GET("movie/popular") Call<MovieResponse> getAllPopularMovies(
      @Query("api_key") String apiKey);
  @GET("movie/top_rated") Call<MovieResponse> getAllTopRatedMovies(
      @Query("api_key") String apiKey);
  @GET("movie/now_playing") Call<MovieResponse> getAllNowPlayingMovies(
      @Query("api_key") String apiKey);
  @GET("movie/upcoming") Call<MovieResponse> getAllUpComingsMovies(
      @Query("api_key") String apiKey);
}
