package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.tv_responses.SingleTvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.TVShowRepository;

public class TVShowsViewModel extends AndroidViewModel
{
  private final TVShowRepository tvShowRepository;

  public TVShowsViewModel(@NonNull Application application)
  {
    super(application);
    tvShowRepository = TVShowRepository.getInstance(application);
  }

  public LiveData<List<SingleTvShowResponse>> getFavoriteTvShows()
  {
    return tvShowRepository.getFavoriteTvShows();
  }

  public LiveData<SingleTvShowResponse> getSingleFavoriteTvShow(int id)
  {
    return tvShowRepository.getSingleFavoriteTvShow(id);
  }

  public void insertFavoriteTvShow(SingleTvShowResponse tv)
  {
    tvShowRepository.insertFavoriteTvShow(tv);
  }

  public void deleteFavoriteTvShow(SingleTvShowResponse tv)
  {
    tvShowRepository.deleteFavoriteTvShow(tv);
  }

  public LiveData<SingleTvShowResponse> findTvShowById(int id)
  {
    return tvShowRepository.findTvShowById(id);
  }

  public LiveData<ArrayList<TvShow>> getAllPopularTvShows(int pageNumber)
  {
    return tvShowRepository.getAllPopularTvShows(pageNumber);
  }

  public LiveData<ArrayList<TvShow>> getAllTopRatedTvShows(int pageNumber)
  {
    return tvShowRepository.getAllTopRatedTvShows(pageNumber);
  }

  public LiveData<ArrayList<TvShow>> getAllOnAirTvShows(int pageNumber)
  {
    return tvShowRepository.getAllOnAirTvShows(pageNumber);
  }

  public LiveData<ArrayList<TvShow>> getAllAiringTodayTvShows(int pageNumber)
  {
    return tvShowRepository.getAllAiringTodayTvShows(pageNumber);
  }

  public LiveData<ArrayList<TvShow>> getAllSearchedTvShows(String arg)
  {
    return tvShowRepository.getAllSearchedTvShows(arg);
  }

  public LiveData<ArrayList<Comment>> getAllComments(int id)
  {
    return tvShowRepository.getTvReviews(id);
  }
}