package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain;

public class User
{
  private String userName;

  public User(String userName)
  {
    this.userName = userName;
  }

  public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }
}
