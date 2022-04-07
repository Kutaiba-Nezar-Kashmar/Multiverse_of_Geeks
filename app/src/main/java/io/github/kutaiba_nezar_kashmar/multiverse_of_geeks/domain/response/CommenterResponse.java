package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Commenter;

public class CommenterResponse
{
  private String avatar_path;

  public Commenter getCommenter()
  {
    return new Commenter(avatar_path);
  }
}
