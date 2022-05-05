package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Cast;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.CastResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.cast_network.CastAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastRepositoryImpl implements CastRepository
{
  private static CastRepositoryImpl instance;
  private final MutableLiveData<ArrayList<Cast>> movieCast;
  private final MutableLiveData<ArrayList<Cast>> tvCast;

  private CastRepositoryImpl()
  {
    movieCast = new MutableLiveData<>();
    tvCast = new MutableLiveData<>();
  }

  public static synchronized CastRepositoryImpl getInstance()
  {
    if (instance == null)
    {
      instance = new CastRepositoryImpl();
    }
    return instance;
  }

  @Override
  public MutableLiveData<ArrayList<Cast>> getMovieCast(int movieId)
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
  public MutableLiveData<ArrayList<Cast>> getTvShowCast(int tvShowId)
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
