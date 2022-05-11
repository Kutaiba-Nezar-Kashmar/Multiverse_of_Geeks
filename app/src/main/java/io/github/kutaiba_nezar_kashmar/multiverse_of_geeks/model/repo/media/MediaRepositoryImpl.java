package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.BuildConfig;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Trending;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.TrendingResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.media.MediaAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaRepositoryImpl implements MediaRepository
{
  private static MediaRepository instance;
  private final MutableLiveData<List<Trending>> trending;

  private MediaRepositoryImpl()
  {
    trending = new MutableLiveData<>();
  }

  public static synchronized MediaRepository getInstance()
  {
    if (instance == null)
    {
      instance = new MediaRepositoryImpl();
    }
    return instance;
  }

  @Override
  public MutableLiveData<List<Trending>> getTrendingToday()
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
