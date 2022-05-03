package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Cast;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CastRepositoryImpl implements CastRepository
{
  private static CastRepositoryImpl instance;

  private CastRepositoryImpl()
  {
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
  public Flowable<ArrayList<Cast>> getMovieCast(int movieId)
  {
    return MediaClient.getCastAPI().getMovieCast(movieId, BuildConfig.API_KEY)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getCastList()));
  }

  @Override
  public Flowable<ArrayList<Cast>> getTvShowCast(int tvShowId)
  {
    return MediaClient.getCastAPI().getTvShowCast(tvShowId, BuildConfig.API_KEY)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getCastList()));
  }
}
