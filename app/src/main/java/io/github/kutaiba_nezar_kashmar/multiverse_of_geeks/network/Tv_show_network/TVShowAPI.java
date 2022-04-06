package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.Tv_show_network;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.TvShowResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TVShowAPI
{
  @GET("tv/popular")
  Call<TvShowResponse> getAllPopularTvShows(@Query("api_key") String apiKey);
}
