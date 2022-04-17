package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.games_network.FreeToPlayAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FreeToPlayGamesServiceGenerator
{
  private static final Retrofit.Builder builder = new Retrofit.Builder()
      .baseUrl("https://www.freetogame.com/api/")
      .addConverterFactory(GsonConverterFactory.create());

  private static final Retrofit retrofit = builder.build();
  private static final FreeToPlayAPI freeToPlayAPI = retrofit.create(FreeToPlayAPI.class);

  public static FreeToPlayAPI getFreeToPlayAPI()
  {
    return freeToPlayAPI;
  }
}
