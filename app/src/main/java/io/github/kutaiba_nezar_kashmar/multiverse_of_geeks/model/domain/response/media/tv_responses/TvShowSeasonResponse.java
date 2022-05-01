package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses;

public class TvShowSeasonResponse
{
  private String air_date;
  private int episode_count;
  private int id;
  private String name;
  private String overview;
  private String poster_path;
  private int season_number;

  public String getAir_date()
  {
    return air_date;
  }

  public int getEpisode_count()
  {
    return episode_count;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getOverview()
  {
    return overview;
  }

  public String getPoster_path()
  {
    return poster_path;
  }

  public int getSeason_number()
  {
    return season_number;
  }
}
