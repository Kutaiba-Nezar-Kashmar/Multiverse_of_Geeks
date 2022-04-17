package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.tv_responses;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.MediaGenreResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.MediaProductionCompaniesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.MediaProductionCountriesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.MediaSpokenLanguagesResponse;

public class SingleTvShowResponse
{
  private boolean adult;
  private String backdrop_path;
  private ArrayList<TvShowCreatorResponse> created_by;
  private ArrayList<Integer> episode_run_time;
  private String first_air_date;
  private ArrayList<MediaGenreResponse> genres;
  private String homepage;
  private int id;
  private boolean in_production;
  private ArrayList<String> languages;
  private String last_air_date;
  private TvShowLastEpisodeToAirResponse last_episode_to_air;
  private String name;
  private ArrayList<TvShowNetworkResponse> networks;
  private int number_of_episodes;
  private int number_of_seasons;
  private ArrayList<String> origin_country;
  private String original_language;
  private String original_name;
  private String overview;
  private float popularity;
  private String poster_path;
  private ArrayList<MediaProductionCompaniesResponse> production_companies;
  private ArrayList<MediaProductionCountriesResponse> production_countries;
  private ArrayList<TvShowSeasonResponse> seasons;
  private ArrayList<MediaSpokenLanguagesResponse> spoken_languages;
  private String status;
  private String tagline;
  private String type;
  private float vote_average;
  private float vote_count;

  public boolean isAdult()
  {
    return adult;
  }

  public String getBackdrop_path()
  {
    return backdrop_path;
  }

  public ArrayList<TvShowCreatorResponse> getCreated_by()
  {
    return created_by;
  }

  public ArrayList<Integer> getEpisode_run_time()
  {
    return episode_run_time;
  }

  public String getFirst_air_date()
  {
    return first_air_date;
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

  public boolean isIn_production()
  {
    return in_production;
  }

  public ArrayList<String> getLanguages()
  {
    return languages;
  }

  public String getLast_air_date()
  {
    return last_air_date;
  }

  public TvShowLastEpisodeToAirResponse getLast_episode_to_air()
  {
    return last_episode_to_air;
  }

  public String getName()
  {
    return name;
  }

  public ArrayList<TvShowNetworkResponse> getNetworks()
  {
    return networks;
  }

  public int getNumber_of_episodes()
  {
    return number_of_episodes;
  }

  public int getNumber_of_seasons()
  {
    return number_of_seasons;
  }

  public ArrayList<String> getOrigin_country()
  {
    return origin_country;
  }

  public String getOriginal_language()
  {
    return original_language;
  }

  public String getOriginal_name()
  {
    return original_name;
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

  public ArrayList<TvShowSeasonResponse> getSeasons()
  {
    return seasons;
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

  public String getType()
  {
    return type;
  }

  public float getVote_average()
  {
    return vote_average;
  }

  public float getVote_count()
  {
    return vote_count;
  }
}
