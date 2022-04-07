package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.cast_network;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.CastResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CastAPI
{
  @GET("movie/{movie_id}/credits")
  Call<CastResponse> getMovieCast(@Path("movie_id") int movie_id,
      @Query("api_key") String apiKey);
}
