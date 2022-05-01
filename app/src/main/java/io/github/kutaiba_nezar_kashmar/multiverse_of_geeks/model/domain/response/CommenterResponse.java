package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Commenter;

public class CommenterResponse
{
  private String avatar_path;

  public Commenter getCommenter()
  {
    return new Commenter(avatar_path);
  }
}
