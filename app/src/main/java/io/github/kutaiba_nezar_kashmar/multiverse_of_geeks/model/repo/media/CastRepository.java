package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Cast;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.CastResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.cast_network.CastAPI;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastRepository
{
  private static CastRepository instance;
  private final MutableLiveData<ArrayList<Cast>> movieCast;
  private final MutableLiveData<ArrayList<Cast>> tvShowCast;

  private CastRepository()
  {
    movieCast = new MutableLiveData<>();
    tvShowCast = new MutableLiveData<>();
  }

  public static synchronized CastRepository getInstance()
  {
    if (instance == null)
    {
      instance = new CastRepository();
    }
    return instance;
  }

  public Flowable<ArrayList<Cast>> getMovieCast(int movieId)
  {
    return MediaClient.getCastAPI().getMovieCast(movieId, BuildConfig.API_KEY)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getCastList()));
  }

  public Flowable<ArrayList<Cast>> getTvShowCast(int tvShowId)
  {
    return MediaClient.getCastAPI().getTvShowCast(tvShowId, BuildConfig.API_KEY)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getCastList()));
  }
}
