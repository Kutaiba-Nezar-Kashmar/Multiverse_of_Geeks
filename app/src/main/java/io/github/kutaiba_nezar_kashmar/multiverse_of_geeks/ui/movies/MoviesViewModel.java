package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.MovieGenre;
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

  public void findById(int id)
  {
    movieRepository.findMovie(id);
  }

  public LiveData<ArrayList<Movie>> getAllPopularMovies()
  {
    return movieRepository.getAllPopularMovies();
  }

  public void setUpGenreList()
  {
    SharedPreferences preferences = getApplication().getSharedPreferences("genrePrefs", Context.MODE_PRIVATE);
    preferences.edit().putString("1", "Action").apply();
    preferences.edit().putString("2", "Adventure").apply();
    preferences.edit().putString("4", "Comedy").apply();
    preferences.edit().putString("5", "Horror").apply();
    preferences.edit().putString("6", "Romance").apply();
    preferences.edit().putString("7", "Animated").apply();
  }

  public ArrayList<MovieGenre> retrievedGenreList()
  {
    SharedPreferences preferences = getApplication().getSharedPreferences("genrePrefs", Context.MODE_PRIVATE);

    ArrayList<MovieGenre> ml = new ArrayList<>();
    ml.add(new MovieGenre(preferences.getString("1", "Action")));
    ml.add(new MovieGenre(preferences.getString("2", "Adventure")));
    ml.add(new MovieGenre(preferences.getString("4", "Comedy")));
    ml.add(new MovieGenre(preferences.getString("5", "Horror")));
    ml.add(new MovieGenre(preferences.getString("6", "Romance")));
    ml.add(new MovieGenre(preferences.getString("7", "Animated")));
    return ml;
  }
}
