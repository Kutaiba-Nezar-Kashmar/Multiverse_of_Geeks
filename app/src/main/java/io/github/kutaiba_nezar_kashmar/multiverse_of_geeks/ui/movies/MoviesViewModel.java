package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.movies_repo.MovieRepository;

public class MoviesViewModel extends AndroidViewModel
{
  private MovieRepository movieRepository;

  public MoviesViewModel(Application application)
  {
    super(application);
    movieRepository = MovieRepository.getInstance();
  }

  public LiveData<Movie> getMovie()
  {
    return movieRepository.getMovie();
  }

  public LiveData<ArrayList<Movie>> getMovies()
  {
    return movieRepository.getPopularMovies();
  }

  public LiveData<Movie> findMovieById(int id)
  {
    return movieRepository.findMovie(id);
  }

  public LiveData<ArrayList<Movie>> getAllPopularMovies()
  {
    return movieRepository.getAllPopularMovies();
  }

  public LiveData<ArrayList<Movie>> getAllLatestMovies()
  {
    return movieRepository.getLatestMovies();
  }

  public LiveData<ArrayList<Movie>> viewMovies(String arg)
  {
    if (arg.equalsIgnoreCase("Latest"))
    {
      return getAllLatestMovies();
    }
    return getAllPopularMovies();
  }
}
