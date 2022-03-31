package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.MovieGenre;

public class MoviesViewModel extends AndroidViewModel
{
  private MutableLiveData<ArrayList<MovieGenre>> genres;
  private MutableLiveData<ArrayList<Movie>> movies;
  private ArrayList<MovieGenre> tempGen;
  private ArrayList<Movie> tempMo;
  public MoviesViewModel(Application application)
  {
    super(application);
    genres = new MutableLiveData<>();
    movies = new MutableLiveData<>();
    tempGen = new ArrayList<>();
    tempMo = new ArrayList<>();
    //TODO: Change later to read from the a model

    tempMo.add(new Movie("PLACEHOLDER MOVIESSS"));
    tempMo.add(new Movie("PLACEHOLDER MOVIESSS"));
    tempMo.add(new Movie("PLACEHOLDER MOVIESSS"));
    tempMo.add(new Movie("PLACEHOLDER MOVIESSS"));
    tempMo.add(new Movie("PLACEHOLDER MOVIESSS"));
    tempMo.add(new Movie("PLACEHOLDER MOVIESSS"));
    tempMo.add(new Movie("PLACEHOLDER MOVIESSS"));
    tempMo.add(new Movie("PLACEHOLDER MOVIESSS"));
    tempMo.add(new Movie("PLACEHOLDER MOVIESSS"));

    /*tempGen.add(new MovieGenre("PLACEHOLDER1", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER2", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER3", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER4", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER5", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER6", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER7", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER8", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER9", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER10", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER11", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER12", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER13", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER14", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER15", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER16", 0));
    tempGen.add(new MovieGenre("PLACEHOLDER17", 0));*/

    genres.setValue(tempGen);
    movies.setValue(tempMo);
  }

  public MutableLiveData<ArrayList<MovieGenre>> getGenres()
  {
    return genres;
  }

  public void setGenres(MutableLiveData<ArrayList<MovieGenre>> genres)
  {
    this.genres = genres;
  }

  public ArrayList<MovieGenre> getTempGen()
  {
    return tempGen;
  }

  public void setTempGen(ArrayList<MovieGenre> tempGen)
  {
    this.tempGen = tempGen;
  }

  public MutableLiveData<ArrayList<Movie>> getMovies()
  {
    return movies;
  }

  public void setMovies(MutableLiveData<ArrayList<Movie>> movies)
  {
    this.movies = movies;
  }

  public ArrayList<Movie> getTempMo()
  {
    return tempMo;
  }

  public void setTempMo(ArrayList<Movie> tempMo)
  {
    this.tempMo = tempMo;
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
