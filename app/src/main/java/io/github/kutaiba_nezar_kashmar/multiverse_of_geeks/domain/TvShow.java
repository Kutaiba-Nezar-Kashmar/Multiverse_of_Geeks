package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain;

public class TvShow
{
  private int id;
  private String name;
  private String first_air_date;
  private String overview;
  private float vote_average;
  private String poster_path;

  public TvShow(int id, String name, String first_air_date, String overview,
      float vote_average, String poster_path)
  {
    this.id = id;
    this.name = name;
    this.first_air_date = first_air_date;
    this.overview = overview;
    this.vote_average = vote_average;
    this.poster_path = poster_path;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getFirst_air_date()
  {
    return first_air_date;
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
