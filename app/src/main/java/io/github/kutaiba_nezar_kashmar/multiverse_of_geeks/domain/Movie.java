package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain;

public class Movie
{
  private final int id;
  private final String title;
  private final String release_date;
  private final String overview;
  private final float vote_average;
  private final String poster_path;

  public Movie(int id, String title, String release_date, String overview,
      float vote_average, String poster_path)
  {
    this.id = id;
    this.title = title;
    this.release_date = release_date;
    this.overview = overview;
    this.vote_average = vote_average;
    this.poster_path = poster_path;
  }

  public int getId()
  {
    return id;
  }

  public String getTitle()
  {
    return title;
  }

  public String getRelease_date()
  {
    return release_date;
  }

  public String getOverview()
  {
    return overview;
  }

  public float getVote_average()
  {
    return vote_average;
  }

  public String getPoster_path()
  {
    return poster_path;
  }
}
