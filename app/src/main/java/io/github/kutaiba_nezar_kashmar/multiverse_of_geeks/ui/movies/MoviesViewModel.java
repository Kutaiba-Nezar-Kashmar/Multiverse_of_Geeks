package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.MovieRepository;

public class MoviesViewModel extends AndroidViewModel
{
  private final MovieRepository movieRepository;

  @RequiresApi(api = Build.VERSION_CODES.O)
  public MoviesViewModel(Application application)
  {
    super(application);
    movieRepository = MovieRepository.getInstance();
  }

  public LiveData<Movie> findMovieById(int id)
  {
    return movieRepository.findMovie(id);
  }

  public LiveData<ArrayList<Movie>> getAllPopularMovies(int pageNumber)
  {
    return movieRepository.getAllPopularMovies(pageNumber);
  }

  public LiveData<ArrayList<Movie>> getAllTopRatedMovies()
  {
    return movieRepository.getAllTopRatedMovies();
  }

  public LiveData<ArrayList<Movie>> getAllNowPlayingMovies()
  {
    return movieRepository.getAllNowPlayingMovies();
  }

  public LiveData<ArrayList<Movie>> getAllUpcomingMovies()
  {
    return movieRepository.getAllUpcomingMovies();
  }

  public LiveData<ArrayList<Movie>> getAllSearchedMoviesMovies(String arg)
  {
    return movieRepository.getAllSearchedMoviesMovies(arg);
  }

  public LiveData<ArrayList<Comment>> getAllComments(int id)
  {
    return movieRepository.getMovieReviews(id);
  }

  public LiveData<ArrayList<Movie>> getAllSimilarMovies(int id)
  {
    return movieRepository.getAllSimilarMovies(id);
  }
}
