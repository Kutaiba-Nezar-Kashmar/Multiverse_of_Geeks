package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Trending;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MediaRepositoryImpl implements MediaRepository
{
  private static MediaRepositoryImpl instance;

  private MediaRepositoryImpl()
  {
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
  public Flowable<ArrayList<Trending>> getTrendingToday()
  {
    return MediaClient.getMediaAPI().getTrendingMediaToday(BuildConfig.API_KEY)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }
}
