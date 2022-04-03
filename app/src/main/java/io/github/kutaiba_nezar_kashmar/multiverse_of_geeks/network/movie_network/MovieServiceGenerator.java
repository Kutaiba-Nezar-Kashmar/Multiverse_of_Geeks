package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.movie_network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieServiceGenerator
{
  private static Retrofit.Builder builder = new Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/3/")
      .addConverterFactory(GsonConverterFactory.create());

  private static Retrofit retrofit = builder.build();
  private static MovieAPI movieAPI = retrofit.create(MovieAPI.class);

  public static MovieAPI getMovieAPI()
  {
    return movieAPI;
  }
}
