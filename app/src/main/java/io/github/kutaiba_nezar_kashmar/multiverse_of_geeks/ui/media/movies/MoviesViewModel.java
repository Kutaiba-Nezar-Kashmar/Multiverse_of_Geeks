package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.movies.MovieRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.movies.MovieRepositoryImpl;

public class MoviesViewModel extends AndroidViewModel
{
  private final MovieRepository movieRepository;

  @RequiresApi(api = Build.VERSION_CODES.O)
  public MoviesViewModel(Application application)
  {
    super(application);
    movieRepository = MovieRepositoryImpl.getInstance(application);
  }

  public LiveData<List<SingleMovieResponse>> getFavoriteMovies()
  {
    return movieRepository.getFavoriteMovies();
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
    return movieRepository.findMovie(id);
  }

  public LiveData<List<Movie>> getAllPopularMovies(int pageNumber)
  {
    return movieRepository.getAllPopularMovies(pageNumber);
  }

  public LiveData<List<Movie>> getAllTopRatedMovies(int pageNumber)
  {
    return movieRepository.getAllTopRatedMovies(pageNumber);
  }

  public LiveData<List<Movie>> getAllNowPlayingMovies(int pageNumber)
  {
    return movieRepository.getAllNowPlayingMovies(pageNumber);
  }

  public LiveData<List<Movie>> getAllUpcomingMovies(int pageNumber)
  {
    return movieRepository.getAllUpcomingMovies(pageNumber);
  }

  public LiveData<List<Movie>> getAllSearchedMoviesMovies(String arg)
  {
    return movieRepository.getAllSearchedMoviesMovies(arg);
  }

  public LiveData<List<Comment>> getAllComments(int id)
  {
    return movieRepository.getMovieReviews(id);
  }

  public LiveData<List<Movie>> getAllSimilarMovies(int id)
  {
    return movieRepository.getAllSimilarMovies(id);
  }
}
