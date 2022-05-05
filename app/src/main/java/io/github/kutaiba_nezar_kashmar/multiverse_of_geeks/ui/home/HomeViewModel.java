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

  public HomeViewModel(@NonNull Application application)
  {
    super(application);
    mediaRepository = MediaRepositoryImpl.getInstance();
  }

  public LiveData<ArrayList<Trending>> getAllTrendingToday()
  {
    return mediaRepository.getTrendingToday();
  }
}