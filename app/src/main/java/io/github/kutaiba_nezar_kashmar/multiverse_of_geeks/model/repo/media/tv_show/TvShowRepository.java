package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.tv_show;

import androidx.lifecycle.LiveData;

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
  Flowable<SingleTvShowResponse> findTvShowById(int id);
  Flowable<ArrayList<TvShow>> getAllPopularTvShows(int pageNumber);
  Flowable<ArrayList<TvShow>> getAllTopRatedTvShows(int pageNumber);
  Flowable<ArrayList<TvShow>> getAllOnAirTvShows(int pageNumber);
  Flowable<ArrayList<TvShow>> getAllAiringTodayTvShows(int pageNumber);
  Flowable<ArrayList<TvShow>> getAllSearchedTvShows(String arg);
  Flowable<ArrayList<Comment>> getTvReviews(int id);
}
