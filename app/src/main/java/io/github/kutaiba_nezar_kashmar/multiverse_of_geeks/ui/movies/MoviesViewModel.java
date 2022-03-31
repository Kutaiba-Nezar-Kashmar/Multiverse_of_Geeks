package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.MovieGenre;

public class MoviesViewModel extends ViewModel
{
  private MutableLiveData<ArrayList<MovieGenre>> genres;
  private MutableLiveData<ArrayList<Movie>> movies;
  private ArrayList<MovieGenre> tempGen;
  private ArrayList<Movie> tempMo;
  public MoviesViewModel()
  {
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

    tempGen.add(new MovieGenre("PLACEHOLDER1", 0));
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
    tempGen.add(new MovieGenre("PLACEHOLDER17", 0));

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
}
