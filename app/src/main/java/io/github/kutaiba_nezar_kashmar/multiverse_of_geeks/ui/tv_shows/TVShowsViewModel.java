package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.TVShowRepository;

public class TVShowsViewModel extends AndroidViewModel
{
  private TVShowRepository tvShowRepository;

  public TVShowsViewModel(@NonNull Application application)
  {
    super(application);
    tvShowRepository = TVShowRepository.getInstance();
  }

  public LiveData<ArrayList<TvShow>> getAllPopularTvShows()
  {
    return tvShowRepository.getAllPopularTvShows();
  }
}
