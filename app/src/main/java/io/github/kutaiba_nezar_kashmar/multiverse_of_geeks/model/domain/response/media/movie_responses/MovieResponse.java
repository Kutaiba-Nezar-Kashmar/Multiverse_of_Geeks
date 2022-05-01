package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Movie;

public class MovieResponse
{
  private int id;
  private String title;
  private String release_date;
  private String overview;
  private float vote_average;
  private String poster_path;
  private ArrayList<Movie> results = null;

  public Movie getMovie()
  {
    return new Movie(id, title, release_date, overview, vote_average,
        poster_path);
  }

  public ArrayList<Movie> getResults()
  {
    return results;
  }
}
