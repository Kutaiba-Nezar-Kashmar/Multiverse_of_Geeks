package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play;

public class AllFreeToPlayGamesResponse
{
  private int id;
  private String title;
  private String thumbnail;
  private String short_description;
  private String game_url;
  private String genre;
  private String platform;
  private String publisher;
  private String developer;
  private String release_date;
  private String freetogame_profile_url;

  public int getId()
  {
    return id;
  }

  public String getTitle()
  {
    return title;
  }

  public String getThumbnail()
  {
    return thumbnail;
  }

  public String getShort_description()
  {
    return short_description;
  }

  public String getGame_url()
  {
    return game_url;
  }

  public String getGenre()
  {
    return genre;
  }

  public String getPlatform()
  {
    return platform;
  }

  public String getPublisher()
  {
    return publisher;
  }

  public String getDeveloper()
  {
    return developer;
  }

  public String getRelease_date()
  {
    return release_date;
  }

  public String getFreetogame_profile_url()
  {
    return freetogame_profile_url;
  }
}
