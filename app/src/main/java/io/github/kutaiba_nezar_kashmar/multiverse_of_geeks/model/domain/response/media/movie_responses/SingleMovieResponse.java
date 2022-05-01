package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaGenreResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaProductionCompaniesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaProductionCountriesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaSpokenLanguagesResponse;

@Entity(tableName = "movie")
public class SingleMovieResponse
{
  @Ignore
  private boolean adult;
  @Ignore
  private MovieCollectionResponse belongs_to_collection;
  private String backdrop_path;
  private int budget;
  @Ignore
  private ArrayList<MediaGenreResponse> genres;
  private String homepage;
  @PrimaryKey
  private int id;
  private String imdb_id;
  private String original_language;
  private String original_title;
  private String overview;
  private float popularity;
  private String poster_path;
  @Ignore
  private ArrayList<MediaProductionCompaniesResponse> production_companies;
  @Ignore
  private ArrayList<MediaProductionCountriesResponse> production_countries;
  private String release_date;
  private int revenue;
  private int runtime;
  @Ignore
  private ArrayList<MediaSpokenLanguagesResponse> spoken_languages;
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

  public ArrayList<MediaGenreResponse> getGenres()
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

  public ArrayList<MediaProductionCompaniesResponse> getProduction_companies()
  {
    return production_companies;
  }

  public ArrayList<MediaProductionCountriesResponse> getProduction_countries()
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

  public ArrayList<MediaSpokenLanguagesResponse> getSpoken_languages()
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

  public void setAdult(boolean adult)
  {
    this.adult = adult;
  }

  public void setBelongs_to_collection(
      MovieCollectionResponse belongs_to_collection)
  {
    this.belongs_to_collection = belongs_to_collection;
  }

  public void setBackdrop_path(String backdrop_path)
  {
    this.backdrop_path = backdrop_path;
  }

  public void setBudget(int budget)
  {
    this.budget = budget;
  }

  public void setGenres(ArrayList<MediaGenreResponse> genres)
  {
    this.genres = genres;
  }

  public void setHomepage(String homepage)
  {
    this.homepage = homepage;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public void setImdb_id(String imdb_id)
  {
    this.imdb_id = imdb_id;
  }

  public void setOriginal_language(String original_language)
  {
    this.original_language = original_language;
  }

  public void setOriginal_title(String original_title)
  {
    this.original_title = original_title;
  }

  public void setOverview(String overview)
  {
    this.overview = overview;
  }

  public void setPopularity(float popularity)
  {
    this.popularity = popularity;
  }

  public void setPoster_path(String poster_path)
  {
    this.poster_path = poster_path;
  }

  public void setProduction_companies(
      ArrayList<MediaProductionCompaniesResponse> production_companies)
  {
    this.production_companies = production_companies;
  }

  public void setProduction_countries(
      ArrayList<MediaProductionCountriesResponse> production_countries)
  {
    this.production_countries = production_countries;
  }

  public void setRelease_date(String release_date)
  {
    this.release_date = release_date;
  }

  public void setRevenue(int revenue)
  {
    this.revenue = revenue;
  }

  public void setRuntime(int runtime)
  {
    this.runtime = runtime;
  }

  public void setSpoken_languages(
      ArrayList<MediaSpokenLanguagesResponse> spoken_languages)
  {
    this.spoken_languages = spoken_languages;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public void setTagline(String tagline)
  {
    this.tagline = tagline;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public void setVideo(String video)
  {
    this.video = video;
  }

  public void setVote_average(float vote_average)
  {
    this.vote_average = vote_average;
  }

  public void setVote_count(int vote_count)
  {
    this.vote_count = vote_count;
  }
}
