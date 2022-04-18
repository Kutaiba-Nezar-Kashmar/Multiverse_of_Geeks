package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games;

public class PlatformsResponse
{
  private int id;
  private String name;
  private String image;
  private String year_end;
  private String year_start;
  private String image_background;
  private String released_at;
  private RequirementResponse requirements_en;

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getImage()
  {
    return image;
  }

  public String getYear_end()
  {
    return year_end;
  }

  public String getYear_start()
  {
    return year_start;
  }

  public String getImage_background()
  {
    return image_background;
  }

  public String getReleased_at()
  {
    return released_at;
  }

  public RequirementResponse getRequirements_en()
  {
    return requirements_en;
  }
}
