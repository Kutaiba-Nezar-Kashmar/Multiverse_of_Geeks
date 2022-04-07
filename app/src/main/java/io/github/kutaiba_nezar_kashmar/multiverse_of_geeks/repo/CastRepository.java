package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Cast;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.CastResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.MovieTVServiceGenerator;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.cast_network.CastAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastRepository
{
  private static CastRepository instance;
  private final MutableLiveData<ArrayList<Cast>> movieCast;

  private CastRepository()
  {
    movieCast = new MutableLiveData<>();
  }

  public static synchronized CastRepository getInstance()
  {
    if (instance == null)
    {
      instance = new CastRepository();
    }
    return instance;
  }

  public MutableLiveData<ArrayList<Cast>> getMovieCast(int movieId)
  {
    CastAPI castAPI = MovieTVServiceGenerator.getCastAPI();
    Call<CastResponse> call = castAPI.getMovieCast(movieId, Constants.API_KEY);
    call.enqueue(new Callback<CastResponse>()
    {
      @Override
      public void onResponse(Call<CastResponse> call,
          Response<CastResponse> response)
      {
        if (response.code() == 200)
        {
          movieCast.setValue(response.body().getCastList());
        }
      }

      @Override
      public void onFailure(Call<CastResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return movieCast;
  }
}
