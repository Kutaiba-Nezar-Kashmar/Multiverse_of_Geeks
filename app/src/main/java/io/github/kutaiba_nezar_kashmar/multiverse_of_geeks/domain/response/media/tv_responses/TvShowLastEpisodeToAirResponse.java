package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.tv_responses;

public class TvShowLastEpisodeToAirResponse
{
  private String air_date;
  private int episode_number;
  private int id;
  private String name;
  private String production_code;
  private int season_number;
  private String still_path;

  public String getAir_date()
  {
    return air_date;
  }

  public int getEpisode_number()
  {
    return episode_number;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getProduction_code()
  {
    return production_code;
  }

  public int getSeason_number()
  {
    return season_number;
  }

  public String getStill_path()
  {
    return still_path;
  }
}
