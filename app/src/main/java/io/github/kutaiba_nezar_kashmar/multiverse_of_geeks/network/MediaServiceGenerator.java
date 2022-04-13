package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.media.MediaAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.media.Tv_show_network.TVShowAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.cast_network.CastAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.media.movie_network.MovieAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MediaServiceGenerator
{
  //instance of retrofit to specify the base URL for the service
  private static final Retrofit.Builder builder = new Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/3/")
      .addConverterFactory(GsonConverterFactory.create());

  private static final Retrofit retrofit = builder.build();
  private static final MediaAPI mediaAPI = retrofit.create(MediaAPI.class);
  private static final MovieAPI movieAPI = retrofit.create(MovieAPI.class);
  private static final TVShowAPI tvShowAPI = retrofit.create(TVShowAPI.class);
  private static final CastAPI castAPI = retrofit.create(CastAPI.class);

  public static MediaAPI getMediaAPI()
  {
    return mediaAPI;
  }

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
