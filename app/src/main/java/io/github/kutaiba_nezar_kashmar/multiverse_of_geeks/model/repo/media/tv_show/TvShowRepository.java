package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.tv_show;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;

public interface TvShowRepository
{
  /**
   * Hold a list of SingleTvShowResponse
   *
   * @return LiveData of al list of SingleTvShowResponse object
   */
  LiveData<List<SingleTvShowResponse>> getFavoriteTvShows();

  /**
   * Add new SingleTvShowResponse object to database
   *
   * @param tv The movie to be added
   */
  void insertFavoriteTvShow(SingleTvShowResponse tv);

  /**
   * Delete new SingleTvShowResponse object to database
   *
   * @param tv The movie to be deleted
   */
  void deleteFavoriteTvShow(SingleTvShowResponse tv);

  /**
   * Hold a SingleTvShowResponse object based on given parameter
   *
   * @param id The tv id
   * @return LiveData of a SingleTvShowResponse object
   */
  LiveData<SingleTvShowResponse> getSingleFavoriteTvShow(int id);

  /**
   * Hold a SingleTvShowResponse object based on given parameter
   *
   * @param id The tv id
   * @return MutableLiveData of a SingleTvShowResponse object
   */
  MutableLiveData<SingleTvShowResponse> findTvShowById(int id);

  /**
   * Hold a list of popular TvShow object based on given parameter
   *
   * @param pageNumber The page number in the webserver that holds a list of tv shows
   * @return MutableLiveData of a list of  TvShow object
   */
  MutableLiveData<List<TvShow>> getAllPopularTvShows(int pageNumber);

  /**
   * Hold a list of top rated TvShow object based on given parameter
   *
   * @param pageNumber The page number in the webserver that holds a list of tv show
   * @return MutableLiveData of a list of  TvShow object
   */
  MutableLiveData<List<TvShow>> getAllTopRatedTvShows(int pageNumber);

  /**
   * Hold a list of in air TvShow object based on given parameter
   *
   * @param pageNumber The page number in the webserver that holds a list of tv show
   * @return MutableLiveData of a list of TvShow object
   */
  MutableLiveData<List<TvShow>> getAllOnAirTvShows(int pageNumber);

  /**
   * Hold a list of airing today TvShow object based on given parameter
   *
   * @param pageNumber The page number in the webserver that holds a list of tv show
   * @return MutableLiveData of a list of TvShow object
   */
  MutableLiveData<List<TvShow>> getAllAiringTodayTvShows(int pageNumber);

  /**
   * Hold a list of queried TvShow object based on given parameter
   *
   * @param arg The query used to search fo a tv show or list of tv shows
   * @return MutableLiveData of a list of  TvShow object
   */
  MutableLiveData<List<TvShow>> getAllSearchedTvShows(String arg);
}
