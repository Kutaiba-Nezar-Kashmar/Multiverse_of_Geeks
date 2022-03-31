package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain;

public class Movie
{
  private String movieName;

  public Movie(String movieName)
  {
    this.movieName = movieName;
  }

  public String getMovieName()
  {
    return movieName;
  }

  public void setMovieName(String movieName)
  {
    this.movieName = movieName;
  }
}
