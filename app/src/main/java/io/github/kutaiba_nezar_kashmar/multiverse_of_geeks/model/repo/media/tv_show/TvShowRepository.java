package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.tv_show;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;
import io.reactivex.rxjava3.core.Flowable;

public interface TvShowRepository
{
  LiveData<List<SingleTvShowResponse>> getFavoriteTvShows();
  void insertFavoriteTvShow(SingleTvShowResponse tv);
  void deleteFavoriteTvShow(SingleTvShowResponse tv);
  LiveData<SingleTvShowResponse> getSingleFavoriteTvShow(int id);
  MutableLiveData<SingleTvShowResponse> findTvShowById(int id);
  MutableLiveData<List<TvShow>> getAllPopularTvShows(int pageNumber);
  MutableLiveData<List<TvShow>> getAllTopRatedTvShows(int pageNumber);
  MutableLiveData<List<TvShow>> getAllOnAirTvShows(int pageNumber);
  MutableLiveData<List<TvShow>> getAllAiringTodayTvShows(int pageNumber);
  MutableLiveData<List<TvShow>> getAllSearchedTvShows(String arg);
  MutableLiveData<List<Comment>> getTvReviews(int id);
}
