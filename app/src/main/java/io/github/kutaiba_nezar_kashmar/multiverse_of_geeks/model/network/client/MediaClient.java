package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.cast_network.CastAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.media.Tv_show_network.TVShowAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.media.movie_network.MovieAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MediaClient
{
  //client
  private static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(
      HttpLoggingInterceptor.Level.BODY);
  private static final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(
      logging).build();

  //instance of retrofit to specify the base URL for the service
  private static final Retrofit.Builder builder = new Retrofit.Builder().baseUrl(
          "https://api.themoviedb.org/3/").client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create());

  private static final Retrofit retrofit = builder.build();
  private static final MovieAPI movieAPI = retrofit.create(MovieAPI.class);
  private static final TVShowAPI tvShowAPI = retrofit.create(TVShowAPI.class);
  private static final CastAPI castAPI = retrofit.create(CastAPI.class);

  public static MovieAPI getMovieAPI()
  {
    return movieAPI;
  }

  public static TVShowAPI getTVShowAPI()
  {
    return tvShowAPI;
  }

  public static CastAPI getCastAPI()
  {
    return castAPI;
  }
}
