package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Game;

public class GamesResponse
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
  private ArrayList<Game> results = null;

  public Game getGame()
  {
    return new Game(id, name, released, tba, background_image, rating, playtime,
        updated, platforms, genres, stores, tags, esrb_rating,
        short_screenshots);
  }

  public ArrayList<Game> getResults()
  {
    return results;
  }
}
