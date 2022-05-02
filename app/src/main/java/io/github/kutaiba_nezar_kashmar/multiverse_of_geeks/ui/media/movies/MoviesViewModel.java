package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.movies.MovieRepository;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MoviesViewModel extends AndroidViewModel
{
  private final MovieRepository movieRepository;
  private MutableLiveData<SingleMovieResponse> singleMovie;
  private MutableLiveData<ArrayList<Movie>> popularMovies;
  private MutableLiveData<ArrayList<Movie>> topRatedMovies;
  private MutableLiveData<ArrayList<Movie>> nowPlayingMovies;
  private MutableLiveData<ArrayList<Movie>> upcomingMovies;
  private MutableLiveData<ArrayList<Movie>> searchMovies;
  private MutableLiveData<ArrayList<Movie>> similarMovies;
  private MutableLiveData<ArrayList<Comment>> comments;

  @RequiresApi(api = Build.VERSION_CODES.O)
  public MoviesViewModel(Application application)
  {
    super(application);
    movieRepository = MovieRepository.getInstance(application);
    singleMovie = new MutableLiveData<>();
    popularMovies = new MutableLiveData<>();
    topRatedMovies = new MutableLiveData<>();
    nowPlayingMovies = new MutableLiveData<>();
    upcomingMovies = new MutableLiveData<>();
    searchMovies = new MutableLiveData<>();
    similarMovies = new MutableLiveData<>();
    comments = new MutableLiveData<>();
  }

  public LiveData<List<SingleMovieResponse>> getFavoriteMovies()
  {
    return movieRepository.getFavoritMovies();
  }

  public LiveData<SingleMovieResponse> getSingleFavoriteMovie(int id)
  {
    return movieRepository.getSingleFavoriteMovie(id);
  }

  public void insertMovie(SingleMovieResponse movie)
  {
    movieRepository.insertFavoriteMovie(movie);
  }

  public void deleteMovie(SingleMovieResponse movie)
  {
    movieRepository.deleteFavoriteMovie(movie);
  }

  public LiveData<SingleMovieResponse> findMovieById(int id)
  {
    movieRepository.findMovie(id).subscribeOn(Schedulers.io())
        .doOnNext(singleMovieResponse -> {
          singleMovie.postValue(singleMovieResponse);
        }).subscribe();
    return singleMovie;
  }

  public LiveData<ArrayList<Movie>> getAllPopularMovies(int pageNumber)
  {
    movieRepository.getAllPopularMovies(pageNumber).subscribeOn(Schedulers.io())
        .doOnNext(movies -> {
          popularMovies.postValue(movies);
        }).subscribe();
    return popularMovies;
  }

  public LiveData<ArrayList<Movie>> getAllTopRatedMovies(int pageNumber)
  {
    movieRepository.getAllTopRatedMovies(pageNumber)
        .subscribeOn(Schedulers.io()).doOnNext(movies -> {
      topRatedMovies.postValue(movies);
    }).subscribe();
    return topRatedMovies;
  }

  public LiveData<ArrayList<Movie>> getAllNowPlayingMovies(int pageNumber)
  {
    movieRepository.getAllNowPlayingMovies(pageNumber)
        .subscribeOn(Schedulers.io()).doOnNext(movies -> {
      nowPlayingMovies.postValue(movies);
    }).subscribe();
    return nowPlayingMovies;
  }

  public LiveData<ArrayList<Movie>> getAllUpcomingMovies(int pageNumber)
  {
    movieRepository.getAllUpcomingMovies(pageNumber)
        .subscribeOn(Schedulers.io()).doOnNext(movies -> {
      upcomingMovies.postValue(movies);
    }).subscribe();
    return upcomingMovies;
  }

  public LiveData<ArrayList<Movie>> getAllSearchedMoviesMovies(String arg)
  {
    movieRepository.getAllSearchedMoviesMovies(arg).subscribeOn(Schedulers.io())
        .doOnNext(movies -> {
          searchMovies.postValue(movies);
        }).subscribe();
    return searchMovies;
  }

  public LiveData<ArrayList<Comment>> getAllComments(int id)
  {
    movieRepository.getMovieReviews(id).subscribeOn(Schedulers.io())
        .doOnNext(comments1 -> {
          comments.postValue(comments1);
        }).subscribe();
    return comments;
  }

  public LiveData<ArrayList<Movie>> getAllSimilarMovies(int id)
  {
    movieRepository.getAllSimilarMovies(id).subscribeOn(Schedulers.io())
        .doOnNext(movies -> {
          similarMovies.postValue(movies);
        }).subscribe();
    return similarMovies;
  }
}
