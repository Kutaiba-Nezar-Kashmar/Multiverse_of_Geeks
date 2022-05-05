package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.media;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.TrendingResponse;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MediaAPI
{
  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query the details of a trending movies and tv shows
   *
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of TrendingResponse for the queried movies and tv shows
   */
  @GET("trending/all/day")
  Call<TrendingResponse> getTrendingMediaToday(@Query("api_key") String apiKey);
}
