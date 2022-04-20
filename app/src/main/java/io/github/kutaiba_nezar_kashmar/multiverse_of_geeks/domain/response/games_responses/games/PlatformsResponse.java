package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games;

public class PlatformsResponse
{
  private GamePlatformDetailsResponse platform;
  private String released_at;
  private RequirementResponse requirements_en;

  public GamePlatformDetailsResponse getPlatform()
  {
    return platform;
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
