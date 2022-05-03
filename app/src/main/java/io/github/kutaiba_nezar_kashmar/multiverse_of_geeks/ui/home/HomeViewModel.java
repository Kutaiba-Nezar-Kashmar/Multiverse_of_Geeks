package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Trending;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.MediaRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.MediaRepositoryImpl;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends AndroidViewModel
{
  private final MediaRepository mediaRepository;
  private MutableLiveData<ArrayList<Trending>> trendingMedia;

  public HomeViewModel(@NonNull Application application)
  {
    super(application);
    mediaRepository = MediaRepositoryImpl.getInstance();
    trendingMedia = new MutableLiveData<>();
  }

  public LiveData<ArrayList<Trending>> getAllTrendingToday()
  {
    mediaRepository.getTrendingToday().subscribeOn(Schedulers.io())
        .doOnNext(trendings -> {
          trendingMedia.postValue(trendings);
        }).subscribe();
    return trendingMedia;
  }
}