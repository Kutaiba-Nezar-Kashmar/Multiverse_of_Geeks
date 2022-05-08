package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.cast;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Cast;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.CastResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.cast_network.CastAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastRepositoryImpl implements CastRepository
{
  private static CastRepository instance;
  private final MutableLiveData<List<Cast>> movieCast;
  private final MutableLiveData<List<Cast>> tvCast;

  private CastRepositoryImpl()
  {
    movieCast = new MutableLiveData<>();
    tvCast = new MutableLiveData<>();
  }

  public static synchronized CastRepository getInstance()
  {
    if (instance == null)
    {
      instance = new CastRepositoryImpl();
    }
    return instance;
  }

  @Override
  public MutableLiveData<List<Cast>> getMovieCast(int movieId)
  {
    CastAPI castAPI = MediaClient.getCastAPI();
    Call<CastResponse> call = castAPI.getMovieCast(movieId, BuildConfig.API_KEY);
    call.enqueue(new Callback<CastResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<CastResponse> call,
          @NonNull Response<CastResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            movieCast.setValue(response.body().getCastList());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<CastResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return movieCast;
  }

  @Override
  public MutableLiveData<List<Cast>> getTvShowCast(int tvShowId)
  {
    CastAPI castAPI = MediaClient.getCastAPI();
    Call<CastResponse> call = castAPI.getTvShowCast(tvShowId, BuildConfig.API_KEY);
    call.enqueue(new Callback<CastResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<CastResponse> call,
          @NonNull Response<CastResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            tvCast.setValue(response.body().getCastList());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<CastResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return tvCast;
  }
}
