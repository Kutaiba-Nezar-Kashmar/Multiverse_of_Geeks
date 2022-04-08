package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Cast;

public class CastResponse
{
  private int id;
  private int gender;
  private String known_for_department;
  private String name;
  private String profile_path;
  private String character;
  private ArrayList<Cast> cast = null;

  public Cast getCast()
  {
    return new Cast(id, gender, known_for_department, name, profile_path,
        character);
  }

  public ArrayList<Cast> getCastList()
  {
    return cast;
  }
}
