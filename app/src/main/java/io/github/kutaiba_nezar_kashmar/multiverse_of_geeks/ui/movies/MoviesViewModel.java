package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.MovieGenre;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.movies.MovieGenreAdapter;

public class MoviesViewModel extends ViewModel
{
  private ArrayList<MovieGenre> movieGenres;
  private MovieGenreAdapter adapter;

  public MoviesViewModel()
  {
    movieGenres = new ArrayList<>();
    //TODO: Change later to read from the a model
    movieGenres = new ArrayList<>();
    movieGenres.add(new MovieGenre("PLACEHOLDER1", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER2", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER3", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER4", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER5", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER6", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER7", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER8", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER9", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER10", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER11", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER12", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER13", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER14", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER15", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER16", 0));
    movieGenres.add(new MovieGenre("PLACEHOLDER17", 0));

    adapter = new MovieGenreAdapter(movieGenres);

  }

  public ArrayList<MovieGenre> getMovieGenres()
  {
    return movieGenres;
  }

  public void setMovieGenres(ArrayList<MovieGenre> movieGenres)
  {
    this.movieGenres = movieGenres;
  }

  public MovieGenreAdapter getAdapter()
  {
    return adapter;
  }

  public void setAdapter(MovieGenreAdapter adapter)
  {
    this.adapter = adapter;
  }
}
