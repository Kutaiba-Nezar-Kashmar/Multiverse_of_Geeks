package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie;

import com.google.firebase.storage.StorageReference;

import java.time.LocalDateTime;
import java.util.Date;

public class MovieComment
{
  private int movieId;
  private String userId;
  private String comment;
  private String username;
  private StorageReference userImage = null;
  private String timeStamp;

  public MovieComment()
  {
  }

  public MovieComment(int movieId, String userID, String comment, String username,
      String timeStamp)
  {
    this.movieId = movieId;
    this.userId = userID;
    this.comment = comment;
    this.username = username;
    this.timeStamp = timeStamp;
  }

  public String getUserId()
  {
    return userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
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

  public StorageReference getUserImage()
  {
    return userImage;
  }

  public void setUserImage(StorageReference userImage)
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
