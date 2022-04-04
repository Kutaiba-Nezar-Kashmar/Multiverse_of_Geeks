package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    return movieRepository.getMovies();
  }

  public void findMovieById(int id)
  {
    movieRepository.findMovie(id);
  }

  public LiveData<ArrayList<Movie>> getAllPopularMovies()
  {
    return movieRepository.getAllPopularMovies();
  }
}
