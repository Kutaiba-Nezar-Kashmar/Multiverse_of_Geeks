package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.games_network.GamesAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GamesClient
{
  private static final Retrofit.Builder builder = new Retrofit.Builder()
      .baseUrl("https://api.rawg.io/api/")
      .addConverterFactory(GsonConverterFactory.create());

  private static final Retrofit retrofit = builder.build();
  private static final GamesAPI gamesAPI = retrofit.create(GamesAPI.class);

  public static GamesAPI gamesAPI()
  {
    return gamesAPI;
  }
}
