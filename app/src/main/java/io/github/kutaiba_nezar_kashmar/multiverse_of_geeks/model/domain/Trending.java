package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain;

public class Trending
{
  private int id;
  private int vote_count;
  private String original_language;
  private String poster_path;
  private String title;
  private float vote_average;
  private String overview;
  private String release_date;
  private float popularity;
  private String media_type;

  public Trending(int id, int vote_count, String original_language,
      String poster_path, String title, float vote_average, String overview,
      String release_date, float popularity, String media_type)
  {
    this.id = id;
    this.vote_count = vote_count;
    this.original_language = original_language;
    this.poster_path = poster_path;
    this.title = title;
    this.vote_average = vote_average;
    this.overview = overview;
    this.release_date = release_date;
    this.popularity = popularity;
    this.media_type = media_type;
  }

  public int getId()
  {
    return id;
  }

  public int getVote_count()
  {
    return vote_count;
  }

  public String getOriginal_language()
  {
    return original_language;
  }

  public String getPoster_path()
  {
    return poster_path;
  }

  public String getTitle()
  {
    return title;
  }

  public float getVote_average()
  {
    return vote_average;
  }

  public String getOverview()
  {
    return overview;
  }

  public String getRelease_date()
  {
    return release_date;
  }

  public float getPopularity()
  {
    return popularity;
  }

  public String getMedia_type()
  {
    return media_type;
  }
}
