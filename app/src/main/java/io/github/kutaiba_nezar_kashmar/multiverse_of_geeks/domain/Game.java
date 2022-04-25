package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GameAgeRating;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GameGenreResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GameScreenShots;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GameTageResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GamesDevelopersResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.PlatformsResponse;

@Entity(tableName = "game")
public class Game
{
  @PrimaryKey
  private int id;
  private String name;
  private String released;
  private boolean tba;
  private String background_image;
  private float rating;
  private int playtime;
  private String updated;
  @Ignore
  private ArrayList<PlatformsResponse> platforms;
  @Ignore
  private ArrayList<GameGenreResponse> genres;
  @Ignore
  private ArrayList<GameTageResponse> tags;
  @Ignore
  private GameAgeRating esrb_rating;
  @Ignore
  private ArrayList<GameScreenShots> short_screenshots;
  @Ignore
  private ArrayList<GamesDevelopersResponse> developers;

  public Game(int id, String name, String released, boolean tba,
      String background_image, float rating, int playtime, String updated)
  {
    this.id = id;
    this.name = name;
    this.released = released;
    this.tba = tba;
    this.background_image = background_image;
    this.rating = rating;
    this.playtime = playtime;
    this.updated = updated;
  }

  public Game(int id, String name, String released, boolean tba,
      String background_image, float rating, int playtime, String updated,
      ArrayList<PlatformsResponse> platforms,
      ArrayList<GameGenreResponse> genres,
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

  public void setId(int id)
  {
    this.id = id;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setReleased(String released)
  {
    this.released = released;
  }

  public void setTba(boolean tba)
  {
    this.tba = tba;
  }

  public void setBackground_image(String background_image)
  {
    this.background_image = background_image;
  }

  public void setRating(float rating)
  {
    this.rating = rating;
  }

  public void setPlaytime(int playtime)
  {
    this.playtime = playtime;
  }

  public void setUpdated(String updated)
  {
    this.updated = updated;
  }

  public void setPlatforms(ArrayList<PlatformsResponse> platforms)
  {
    this.platforms = platforms;
  }

  public void setGenres(ArrayList<GameGenreResponse> genres)
  {
    this.genres = genres;
  }

  public void setTags(ArrayList<GameTageResponse> tags)
  {
    this.tags = tags;
  }

  public void setEsrb_rating(GameAgeRating esrb_rating)
  {
    this.esrb_rating = esrb_rating;
  }

  public void setShort_screenshots(ArrayList<GameScreenShots> short_screenshots)
  {
    this.short_screenshots = short_screenshots;
  }

  public void setDevelopers(ArrayList<GamesDevelopersResponse> developers)
  {
    this.developers = developers;
  }
}
