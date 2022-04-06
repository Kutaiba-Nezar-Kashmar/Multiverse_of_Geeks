package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.TvShow;

public class TvShowResponse
{
  private int id;
  private String name;
  private String first_air_date;
  private String overview;
  private float vote_average;
  private String poster_path;
  private ArrayList<TvShow> results = null;

  public TvShow getTvShow()
  {
    return new TvShow(id, name, first_air_date, overview, vote_average,
        poster_path);
  }

  public ArrayList<TvShow> getResults()
  {
    return results;
  }
}
