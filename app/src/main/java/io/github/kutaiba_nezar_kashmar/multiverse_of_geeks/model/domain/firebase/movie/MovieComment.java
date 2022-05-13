package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie;

import java.time.LocalDateTime;
import java.util.Date;

public class MovieComment
{
  private int movieId;
  private String comment;
  private String username;
  private String userImage;
  private String timeStamp;

  public MovieComment()
  {
  }

  public MovieComment(int movieId, String comment, String username,
      String userImage, String timeStamp)
  {
    this.movieId = movieId;
    this.comment = comment;
    this.username = username;
    this.userImage = userImage;
    this.timeStamp = timeStamp;
  }

  public int getMovieId()
  {
    return movieId;
  }

  public void setMovieId(int movieId)
  {
    this.movieId = movieId;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getUserImage()
  {
    return userImage;
  }

  public void setUserImage(String userImage)
  {
    this.userImage = userImage;
  }

  public String getTimeStamp()
  {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp)
  {
    this.timeStamp = timeStamp;
  }
}
