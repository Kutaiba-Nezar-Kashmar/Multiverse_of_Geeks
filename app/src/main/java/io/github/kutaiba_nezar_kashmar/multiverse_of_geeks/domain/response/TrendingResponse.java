package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Trending;

public class TrendingResponse
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
  private ArrayList<Trending> results = null;

  public Trending getTrending()
  {
    return new Trending(id, vote_count, original_language, poster_path, title,
        vote_average, overview, release_date, popularity, media_type);
  }

  public ArrayList<Trending> getResults()
  {
    return results;
  }
}
