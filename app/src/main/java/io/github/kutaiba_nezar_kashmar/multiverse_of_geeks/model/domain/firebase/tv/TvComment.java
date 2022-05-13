package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv;

public class TvComment
{
  private int tvId;
  private String comment;
  private String username;
  private String userImage;
  private String timeStamp;

  public TvComment()
  {
  }

  public TvComment(int tvId, String comment, String username, String userImage,
      String timeStamp)
  {
    this.tvId = tvId;
    this.comment = comment;
    this.username = username;
    this.userImage = userImage;
    this.timeStamp = timeStamp;
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
