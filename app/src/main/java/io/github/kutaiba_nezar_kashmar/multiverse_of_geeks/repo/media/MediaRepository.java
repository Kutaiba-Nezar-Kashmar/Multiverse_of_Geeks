package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.media;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Trending;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.TrendingResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.MediaServiceGenerator;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.media.MediaAPI;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaRepository
{
  private static MediaRepository instance;
  private final MutableLiveData<ArrayList<Trending>> trendingToday;

  private MediaRepository()
  {
    trendingToday = new MutableLiveData<>();
  }

  public static synchronized MediaRepository getInstance()
  {
    if (instance == null)
    {
      instance = new MediaRepository();
    }
    return instance;
  }

  public MutableLiveData<ArrayList<Trending>> getTrendingToday()
  {
    MediaAPI mediaAPI = MediaServiceGenerator.getMediaAPI();
    Call<TrendingResponse> call = mediaAPI
        .getTrendingMediaToday(BuildConfig.API_KEY);
    call.enqueue(new Callback<TrendingResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<TrendingResponse> call,
          @NonNull Response<TrendingResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            trendingToday.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<TrendingResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return trendingToday;
  }
}
