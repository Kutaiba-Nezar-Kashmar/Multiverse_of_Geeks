package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv;

import com.google.firebase.storage.StorageReference;

public class TvComment
{
  private int tvId;
  private String userId;
  private String comment;
  private String username;
  private StorageReference userImage;
  private String timeStamp;

  public TvComment()
  {
  }

  public TvComment(int tvId, String userId, String comment, String username,
      String timeStamp)
  {
    this.tvId = tvId;
    this.userId = userId;
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

  public int getTvId()
  {
    return tvId;
  }

  public void setTvId(int tvId)
  {
    this.tvId = tvId;
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
