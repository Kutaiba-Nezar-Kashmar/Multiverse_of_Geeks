package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.movie_responses;

import java.util.ArrayList;

public class SingleMovieResponse
{
  private boolean adult;
  private MovieCollectionResponse belongs_to_collection;
  private String backdrop_path;
  private int budget;
  private ArrayList<MovieGenreResponse> genres;
  private String homepage;
  private int id;
  private String imdb_id;
  private String original_language;
  private String original_title;
  private String overview;
  private float popularity;
  private String poster_path;
  private ArrayList<MoviesProductionCompaniesResponse> production_companies;
  private ArrayList<MovieProductionCountriesResponse> production_countries;
  private String release_date;
  private int revenue;
  private int runtime;
  private ArrayList<MovieSpokenLanguagesResponse> spoken_languages;
  private String status;
  private String tagline;
  private String title;
  private String video;
  private float vote_average;
  private int vote_count;

  public boolean isAdult()
  {
    return adult;
  }

  public MovieCollectionResponse getBelongs_to_collection()
  {
    return belongs_to_collection;
  }

  public String getBackdrop_path()
  {
    return backdrop_path;
  }

  public int getBudget()
  {
    return budget;
  }

  public ArrayList<MovieGenreResponse> getGenres()
  {
    return genres;
  }

  public String getHomepage()
  {
    return homepage;
  }

  public int getId()
  {
    return id;
  }

  public String getImdb_id()
  {
    return imdb_id;
  }

  public String getOriginal_language()
  {
    return original_language;
  }

  public String getOriginal_title()
  {
    return original_title;
  }

  public String getOverview()
  {
    return overview;
  }

  public float getPopularity()
  {
    return popularity;
  }

  public String getPoster_path()
  {
    return poster_path;
  }

  public ArrayList<MoviesProductionCompaniesResponse> getProduction_companies()
  {
    return production_companies;
  }

  public ArrayList<MovieProductionCountriesResponse> getProduction_countries()
  {
    return production_countries;
  }

  public String getRelease_date()
  {
    return release_date;
  }

  public int getRevenue()
  {
    return revenue;
  }

  public int getRuntime()
  {
    return runtime;
  }

  public ArrayList<MovieSpokenLanguagesResponse> getSpoken_languages()
  {
    return spoken_languages;
  }

  public String getStatus()
  {
    return status;
  }

  public String getTagline()
  {
    return tagline;
  }

  public String getTitle()
  {
    return title;
  }

  public String getVideo()
  {
    return video;
  }

  public float getVote_average()
  {
    return vote_average;
  }

  public int getVote_count()
  {
    return vote_count;
  }
}
