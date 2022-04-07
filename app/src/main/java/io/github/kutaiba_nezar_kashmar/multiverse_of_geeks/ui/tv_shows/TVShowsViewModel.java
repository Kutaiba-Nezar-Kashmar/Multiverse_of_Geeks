package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Comment;
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

  public LiveData<TvShow> findTvShowById(int id)
  {
    return tvShowRepository.findTvShowById(id);
  }

  public LiveData<ArrayList<TvShow>> getAllPopularTvShows()
  {
    return tvShowRepository.getAllPopularTvShows();
  }

  public LiveData<ArrayList<TvShow>> getAllTopRatedTvShows()
  {
    return tvShowRepository.getAllTopRatedTvShows();
  }

  public LiveData<ArrayList<TvShow>> getAllOnAirTvShows()
  {
    return tvShowRepository.getAllOnAirTvShows();
  }

  public LiveData<ArrayList<TvShow>> getAllAiringTodayTvShows()
  {
    return tvShowRepository.getAllAiringTodayTvShows();
  }

  public LiveData<ArrayList<TvShow>> getAllSearchedTvShows(String arg)
  {
    return tvShowRepository.getAllSearchedTvShows(arg);
  }

  public LiveData<ArrayList<Comment>> getAllComments()
  {
    return null;
  }
}
