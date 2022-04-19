package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GameAgeRating;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GameGenreResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GameScreenShots;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GameStoreResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GameTageResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GamesDevelopersResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.PlatformsResponse;

public class Game
{
  private int id;
  private String name;
  private String released;
  private boolean tba;
  private String background_image;
  private float rating;
  private int playtime;
  private String updated;
  private ArrayList<PlatformsResponse> platforms;
  private ArrayList<GameGenreResponse> genres;
  private ArrayList<GameStoreResponse> stores;
  private ArrayList<GameTageResponse> tags;
  private GameAgeRating esrb_rating;
  private ArrayList<GameScreenShots> short_screenshots;
  private ArrayList<GamesDevelopersResponse> developers;

  public Game(int id, String name, String released, boolean tba,
      String background_image, float rating, int playtime, String updated,
      ArrayList<PlatformsResponse> platforms,
      ArrayList<GameGenreResponse> genres, ArrayList<GameStoreResponse> stores,
      ArrayList<GameTageResponse> tags, GameAgeRating esrb_rating,
      ArrayList<GameScreenShots> short_screenshots, ArrayList<GamesDevelopersResponse> developers)
  {
    this.id = id;
    this.name = name;
    this.released = released;
    this.tba = tba;
    this.background_image = background_image;
    this.rating = rating;
    this.playtime = playtime;
    this.updated = updated;
    this.platforms = platforms;
    this.genres = genres;
    this.stores = stores;
    this.tags = tags;
    this.esrb_rating = esrb_rating;
    this.short_screenshots = short_screenshots;
    this.developers = developers;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getReleased()
  {
    return released;
  }

  public boolean isTba()
  {
    return tba;
  }

  public String getBackground_image()
  {
    return background_image;
  }

  public float getRating()
  {
    return rating;
  }

  public int getPlaytime()
  {
    return playtime;
  }

  public String getUpdated()
  {
    return updated;
  }

  public ArrayList<PlatformsResponse> getPlatforms()
  {
    return platforms;
  }

  public ArrayList<GameGenreResponse> getGenres()
  {
    return genres;
  }

  public ArrayList<GameStoreResponse> getStores()
  {
    return stores;
  }

  public ArrayList<GameTageResponse> getTags()
  {
    return tags;
  }

  public GameAgeRating getEsrb_rating()
  {
    return esrb_rating;
  }

  public ArrayList<GameScreenShots> getShort_screenshots()
  {
    return short_screenshots;
  }

  public ArrayList<GamesDevelopersResponse> getDevelopers()
  {
    return developers;
  }
}
