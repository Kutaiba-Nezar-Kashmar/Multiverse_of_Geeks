package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain;

public class Cast
{
  private  int id;
  private int gender;
  private String known_for_department;
  private String name;
  private String profile_path;
  private String character;

  public Cast(int id, int gender, String known_for_department, String name,
      String profile_path, String character)
  {
    this.id = id;
    this.gender = gender;
    this.known_for_department = known_for_department;
    this.name = name;
    this.profile_path = profile_path;
    this.character = character;
  }

  public int getId()
  {
    return id;
  }

  public int getGender()
  {
    return gender;
  }

  public String getKnown_for_department()
  {
    return known_for_department;
  }

  public String getName()
  {
    return name;
  }

  public String getProfile_path()
  {
    return profile_path;
  }

  public String getCharacter()
  {
    return character;
  }
}
