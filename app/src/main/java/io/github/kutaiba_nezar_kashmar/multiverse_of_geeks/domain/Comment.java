package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain;

import java.time.LocalDateTime;

public class Comment
{
  private String text;
  private String timeStamp;
  private User user;

  public Comment(String text, String timeStamp, User user)
  {
    this.text = text;
    this.timeStamp = timeStamp;
    this.user = user;
  }

  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = text;
  }

  public String getTimeStamp()
  {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp)
  {
    this.timeStamp = timeStamp;
  }

  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }
}
