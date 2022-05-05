package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.tv_show.TVShowRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.tv_show.TvShowRepository;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TVShowsViewModel extends AndroidViewModel
{
  private TvShowRepository tvShowRepository;

  public TVShowsViewModel(@NonNull Application application)
  {
    super(application);
    tvShowRepository = TVShowRepositoryImpl.getInstance(application);
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

  public LiveData<List<TvShow>> getAllPopularTvShows(int pageNumber)
  {
    return tvShowRepository.getAllPopularTvShows(pageNumber);
  }

  public LiveData<List<TvShow>> getAllTopRatedTvShows(int pageNumber)
  {
    return tvShowRepository.getAllTopRatedTvShows(pageNumber);
  }

  public LiveData<List<TvShow>> getAllOnAirTvShows(int pageNumber)
  {
    return tvShowRepository.getAllOnAirTvShows(pageNumber);
  }

  public LiveData<List<TvShow>> getAllAiringTodayTvShows(int pageNumber)
  {
    return tvShowRepository.getAllAiringTodayTvShows(pageNumber);
  }

  public LiveData<List<TvShow>> getAllSearchedTvShows(String arg)
  {
    return tvShowRepository.getAllSearchedTvShows(arg);
  }

  public LiveData<List<Comment>> getAllComments(int id)
  {
    return tvShowRepository.getTvReviews(id);
  }
}
