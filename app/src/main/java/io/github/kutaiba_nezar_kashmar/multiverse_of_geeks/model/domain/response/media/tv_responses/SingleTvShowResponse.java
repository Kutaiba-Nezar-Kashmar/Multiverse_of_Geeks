package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaGenreResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaProductionCompaniesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaProductionCountriesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaSpokenLanguagesResponse;

@Entity(tableName = "tv")
public class SingleTvShowResponse
{
  @Ignore
  private boolean adult;
  private String backdrop_path;
  @Ignore
  private ArrayList<TvShowCreatorResponse> created_by;
  @Ignore
  private ArrayList<Integer> episode_run_time;
  private String first_air_date;
  @Ignore
  private ArrayList<MediaGenreResponse> genres;
  private String homepage;
  @PrimaryKey
  private int id;
  private boolean in_production;
  @Ignore
  private ArrayList<String> languages;
  private String last_air_date;
  @Ignore
  private TvShowLastEpisodeToAirResponse last_episode_to_air;
  private String name;
  @Ignore
  private ArrayList<TvShowNetworkResponse> networks;
  private int number_of_episodes;
  private int number_of_seasons;
  @Ignore
  private ArrayList<String> origin_country;
  private String original_language;
  private String original_name;
  private String overview;
  private float popularity;
  private String poster_path;
  @Ignore
  private ArrayList<MediaProductionCompaniesResponse> production_companies;
  @Ignore
  private ArrayList<MediaProductionCountriesResponse> production_countries;
  @Ignore
  private ArrayList<TvShowSeasonResponse> seasons;
  @Ignore
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

  public void setAdult(boolean adult)
  {
    this.adult = adult;
  }

  public void setBackdrop_path(String backdrop_path)
  {
    this.backdrop_path = backdrop_path;
  }

  public void setCreated_by(ArrayList<TvShowCreatorResponse> created_by)
  {
    this.created_by = created_by;
  }

  public void setEpisode_run_time(ArrayList<Integer> episode_run_time)
  {
    this.episode_run_time = episode_run_time;
  }

  public void setFirst_air_date(String first_air_date)
  {
    this.first_air_date = first_air_date;
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

  public void setIn_production(boolean in_production)
  {
    this.in_production = in_production;
  }

  public void setLanguages(ArrayList<String> languages)
  {
    this.languages = languages;
  }

  public void setLast_air_date(String last_air_date)
  {
    this.last_air_date = last_air_date;
  }

  public void setLast_episode_to_air(
      TvShowLastEpisodeToAirResponse last_episode_to_air)
  {
    this.last_episode_to_air = last_episode_to_air;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setNetworks(ArrayList<TvShowNetworkResponse> networks)
  {
    this.networks = networks;
  }

  public void setNumber_of_episodes(int number_of_episodes)
  {
    this.number_of_episodes = number_of_episodes;
  }

  public void setNumber_of_seasons(int number_of_seasons)
  {
    this.number_of_seasons = number_of_seasons;
  }

  public void setOrigin_country(ArrayList<String> origin_country)
  {
    this.origin_country = origin_country;
  }

  public void setOriginal_language(String original_language)
  {
    this.original_language = original_language;
  }

  public void setOriginal_name(String original_name)
  {
    this.original_name = original_name;
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

  public void setSeasons(ArrayList<TvShowSeasonResponse> seasons)
  {
    this.seasons = seasons;
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

  public void setType(String type)
  {
    this.type = type;
  }

  public void setVote_average(float vote_average)
  {
    this.vote_average = vote_average;
  }

  public void setVote_count(float vote_count)
  {
    this.vote_count = vote_count;
  }
}
