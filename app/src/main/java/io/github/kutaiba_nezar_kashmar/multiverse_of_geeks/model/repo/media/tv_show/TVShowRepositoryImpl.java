package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.tv_show;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.GeekDatabase;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.tv.TVShowDAO;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TVShowRepositoryImpl implements TvShowRepository
{
  private static TVShowRepositoryImpl instance;
  private final ExecutorService executorService;
  private final TVShowDAO tvShowDAO;
  private final LiveData<List<SingleTvShowResponse>> favoriteTvShows;
  private final LiveData<SingleTvShowResponse> singleFavoriteTvShow;

  private TVShowRepositoryImpl(Application application)
  {
    GeekDatabase database = GeekDatabase.getInstance(application);
    tvShowDAO = database.tvShowDAO();
    executorService = Executors.newFixedThreadPool(2);
    favoriteTvShows = tvShowDAO.getAllFavoriteTvShows();
    singleFavoriteTvShow = new MutableLiveData<>();
  }

  public static synchronized TVShowRepositoryImpl getInstance(
      Application application)
  {
    if (instance == null)
    {
      instance = new TVShowRepositoryImpl(application);
    }
    return instance;
  }

  @Override
  public LiveData<List<SingleTvShowResponse>> getFavoriteTvShows()
  {
    return favoriteTvShows;
  }

  @Override
  public void insertFavoriteTvShow(SingleTvShowResponse tv)
  {
    executorService.execute(() -> tvShowDAO.insertTvShow(tv));
  }

  @Override
  public void deleteFavoriteTvShow(SingleTvShowResponse tv)
  {
    executorService.execute(() -> tvShowDAO.deleteTvShow(tv));
  }

  @Override
  public LiveData<SingleTvShowResponse> getSingleFavoriteTvShow(int id)
  {
    return tvShowDAO.getTvShowById(id);
  }

  @Override
  public Flowable<SingleTvShowResponse> findTvShowById(int id)
  {
    return MediaClient.getTVShowAPI().getTvShowById(id, BuildConfig.API_KEY)
        .subscribeOn(Schedulers.io()).flatMap(Flowable::just);
  }

  @Override
  public Flowable<ArrayList<TvShow>> getAllPopularTvShows(int pageNumber)
  {
    return MediaClient.getTVShowAPI()
        .getAllPopularTvShows(BuildConfig.API_KEY, pageNumber)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<TvShow>> getAllTopRatedTvShows(int pageNumber)
  {
    return MediaClient.getTVShowAPI()
        .getAllTopRatedTvShows(BuildConfig.API_KEY, pageNumber)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<TvShow>> getAllOnAirTvShows(int pageNumber)
  {
    return MediaClient.getTVShowAPI()
        .getAllOnAirTvShows(BuildConfig.API_KEY, pageNumber)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<TvShow>> getAllAiringTodayTvShows(int pageNumber)
  {
    return MediaClient.getTVShowAPI()
        .getAllAiringTodayTvShows(BuildConfig.API_KEY, pageNumber)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<TvShow>> getAllSearchedTvShows(String arg)
  {
    return MediaClient.getTVShowAPI().searchForTvShow(BuildConfig.API_KEY, arg)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<Comment>> getTvReviews(int id)
  {
    return MediaClient.getTVShowAPI().getTvReviews(id, BuildConfig.API_KEY)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }
}
