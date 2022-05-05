package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Trending;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.TrendingResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.media.MediaAPI;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaRepositoryImpl implements MediaRepository
{
  private static MediaRepositoryImpl instance;
  private final MutableLiveData<ArrayList<Trending>> trending;

  private MediaRepositoryImpl()
  {
    trending = new MutableLiveData<>();
  }

  public static synchronized MediaRepositoryImpl getInstance()
  {
    if (instance == null)
    {
      instance = new MediaRepositoryImpl();
    }
    return instance;
  }

  @Override
  public MutableLiveData<ArrayList<Trending>> getTrendingToday()
  {
    MediaAPI mediaAPI = MediaClient.getMediaAPI();
    Call<TrendingResponse> call = mediaAPI
        .getTrendingMediaToday(BuildConfig.API_KEY);
    call.enqueue(new Callback<TrendingResponse>()
    {
      @Override
      public void onResponse(Call<TrendingResponse> call,
          Response<TrendingResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            trending.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<TrendingResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return trending;
  }
}
