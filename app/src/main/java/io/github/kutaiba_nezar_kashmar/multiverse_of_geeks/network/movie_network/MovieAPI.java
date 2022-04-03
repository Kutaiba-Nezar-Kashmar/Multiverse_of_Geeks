package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.movie_network;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieAPI
{
  //TODO:Rename to get by genre
  //get movie by genre 28: discover/movie?api_key=ee89f342735e902ec9459f6edb50013a&with_genres=28
  @GET("movie/{movie_id}?api_key=ee89f342735e902ec9459f6edb50013a&language=en-US") Call<MovieResponse> getMovies(@Path("movie_id") int movie_id);
}
