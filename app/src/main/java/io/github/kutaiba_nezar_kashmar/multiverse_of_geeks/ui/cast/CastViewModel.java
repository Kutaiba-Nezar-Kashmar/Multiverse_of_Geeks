package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.cast;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Cast;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.CastRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.CastRepositoryImpl;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CastViewModel extends AndroidViewModel
{
  private final CastRepository castRepository;
  private MutableLiveData<ArrayList<Cast>> movieCast;
  private MutableLiveData<ArrayList<Cast>> tvCast;

  public CastViewModel(@NonNull Application application)
  {
    super(application);
    castRepository = CastRepositoryImpl.getInstance();
    movieCast = new MutableLiveData<>();
    tvCast = new MutableLiveData<>();
  }

  public LiveData<ArrayList<Cast>> getMovieCast(int movieId)
  {
    castRepository.getMovieCast(movieId).subscribeOn(Schedulers.io())
        .doOnNext(cast -> {
          movieCast.postValue(cast);
        }).subscribe();
    return movieCast;
  }

  public LiveData<ArrayList<Cast>> getTvShowCast(int tvShowId)
  {
    castRepository.getMovieCast(tvShowId).subscribeOn(Schedulers.io())
        .doOnNext(cast -> {
          tvCast.postValue(cast);
        }).subscribe();
    return tvCast;
  }
}
