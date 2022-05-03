package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows;

import android.app.Application;

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
  private MutableLiveData<SingleTvShowResponse> singleTvShow;
  private MutableLiveData<ArrayList<TvShow>> popularTvShows;
  private MutableLiveData<ArrayList<TvShow>> topRatedTvShows;
  private MutableLiveData<ArrayList<TvShow>> onAirTvShows;
  private MutableLiveData<ArrayList<TvShow>> airingTodayTvShows;
  private MutableLiveData<ArrayList<TvShow>> searchedTvShows;
  private MutableLiveData<ArrayList<Comment>> tvShowReviews;

  public TVShowsViewModel(@NonNull Application application)
  {
    super(application);
    tvShowRepository = TVShowRepositoryImpl.getInstance(application);
    singleTvShow = new MutableLiveData<>();
    popularTvShows = new MutableLiveData<>();
    topRatedTvShows = new MutableLiveData<>();
    onAirTvShows = new MutableLiveData<>();
    airingTodayTvShows = new MutableLiveData<>();
    searchedTvShows = new MutableLiveData<>();
    tvShowReviews = new MutableLiveData<>();
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
    tvShowRepository.findTvShowById(id).subscribeOn(Schedulers.io())
        .doOnNext(singleTvShowResponse -> {
          singleTvShow.postValue(singleTvShowResponse);
        }).subscribe();
    return singleTvShow;
  }

  public LiveData<ArrayList<TvShow>> getAllPopularTvShows(int pageNumber)
  {
    tvShowRepository.getAllPopularTvShows(pageNumber)
        .subscribeOn(Schedulers.io()).doOnNext(tvShows -> {
      popularTvShows.postValue(tvShows);
    }).subscribe();
    return popularTvShows;
  }

  public LiveData<ArrayList<TvShow>> getAllTopRatedTvShows(int pageNumber)
  {
    tvShowRepository.getAllTopRatedTvShows(pageNumber)
        .subscribeOn(Schedulers.io()).doOnNext(tvShows -> {
      topRatedTvShows.postValue(tvShows);
    }).subscribe();
    return topRatedTvShows;
  }

  public LiveData<ArrayList<TvShow>> getAllOnAirTvShows(int pageNumber)
  {
    tvShowRepository.getAllOnAirTvShows(pageNumber).subscribeOn(Schedulers.io())
        .doOnNext(tvShows -> {
          onAirTvShows.postValue(tvShows);
        }).subscribe();
    return onAirTvShows;
  }

  public LiveData<ArrayList<TvShow>> getAllAiringTodayTvShows(int pageNumber)
  {
    tvShowRepository.getAllAiringTodayTvShows(pageNumber)
        .subscribeOn(Schedulers.io()).doOnNext(tvShows -> {
      airingTodayTvShows.postValue(tvShows);
    }).subscribe();
    return airingTodayTvShows;
  }

  public LiveData<ArrayList<TvShow>> getAllSearchedTvShows(String arg)
  {
    tvShowRepository.getAllSearchedTvShows(arg).subscribeOn(Schedulers.io())
        .doOnNext(tvShows -> {
          searchedTvShows.postValue(tvShows);
        }).subscribe();
    return searchedTvShows;
  }

  public LiveData<ArrayList<Comment>> getAllComments(int id)
  {
    tvShowRepository.getTvReviews(id).subscribeOn(Schedulers.io())
        .doOnNext(comments -> {
          tvShowReviews.postValue(comments);
        }).subscribe();
    return tvShowReviews;
  }
}
