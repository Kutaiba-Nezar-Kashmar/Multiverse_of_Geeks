package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.movies.MoviesDAO;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.GeekDatabase;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.CommentResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.MovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.media.movie_network.MovieAPI;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepositoryImpl implements MovieRepository
{
  private final LiveData<List<SingleMovieResponse>> favoritMovies;
  private final LiveData<SingleMovieResponse> singleFavoriteMovie;
  private final ExecutorService executorService;
  private final MoviesDAO moviesDAO;

  public MovieRepositoryImpl(Application application)
  {
    GeekDatabase database = GeekDatabase.getInstance(application);
    moviesDAO = database.moviesDAO();
    executorService = Executors.newFixedThreadPool(2);
    favoritMovies = moviesDAO.getAllFavoriteMovies();
    singleFavoriteMovie = new MutableLiveData<>();
  }

  @Override
  public LiveData<List<SingleMovieResponse>> getFavoriteMovies()
  {
    return favoritMovies;
  }

  @Override
  public void insertFavoriteMovie(SingleMovieResponse movie)
  {
    executorService.execute(() -> moviesDAO.insertMovie(movie));
  }

  @Override
  public void deleteFavoriteMovie(SingleMovieResponse movie)
  {
    executorService.execute(() -> moviesDAO.deleteMovie(movie));
  }

  @Override
  public LiveData<SingleMovieResponse> getSingleFavoriteMovie(int id)
  {
    return moviesDAO.getMovieById(id);
  }

  @Override
  public Flowable<SingleMovieResponse> findMovie(int id)
  {
    return MediaClient.getMovieAPI().getMovieById(id, BuildConfig.API_KEY)
        .subscribeOn(Schedulers.io()).flatMap(Flowable::just);
  }

  @Override
  public Flowable<ArrayList<Movie>> getAllPopularMovies(int pageNumber)
  {
    return MediaClient.getMovieAPI()
        .getAllPopularMovies(BuildConfig.API_KEY, pageNumber)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<Movie>> getAllTopRatedMovies(int pageNumber)
  {
    return MediaClient.getMovieAPI()
        .getAllTopRatedMovies(BuildConfig.API_KEY, pageNumber)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<Movie>> getAllNowPlayingMovies(int pageNumber)
  {
    return MediaClient.getMovieAPI()
        .getAllNowPlayingMovies(BuildConfig.API_KEY, pageNumber)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<Movie>> getAllLatestMovies(int pageNumber)
  {
    return MediaClient.getMovieAPI()
        .getAllLatestMovies(BuildConfig.API_KEY, pageNumber)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<Movie>> getAllUpcomingMovies(int pageNumber)
  {
    return MediaClient.getMovieAPI()
        .getAllUpComingsMovies(BuildConfig.API_KEY, pageNumber)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<Movie>> getAllSearchedMoviesMovies(String query)
  {
    return MediaClient.getMovieAPI().searchForMovie(BuildConfig.API_KEY, query)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<Movie>> getAllSimilarMovies(int id)
  {
    return MediaClient.getMovieAPI().getSimilarMovies(id, BuildConfig.API_KEY)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }

  @Override
  public Flowable<ArrayList<Comment>> getMovieReviews(int id)
  {
    return MediaClient.getMovieAPI().getMovieReviews(id, BuildConfig.API_KEY)
        .subscribeOn(Schedulers.io())
        .flatMap(item -> Flowable.just(item.getResults()));
  }
}
