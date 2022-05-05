package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Trending;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.MediaRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.MediaRepositoryImpl;

public class HomeViewModel extends AndroidViewModel
{
  private final MediaRepository mediaRepository;

  public HomeViewModel(@NonNull Application application)
  {
    super(application);
    mediaRepository = MediaRepositoryImpl.getInstance();
  }

  public LiveData<List<Trending>> getAllTrendingToday()
  {
    return mediaRepository.getTrendingToday();
  }
}