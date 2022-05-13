package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game;

public class GameComment
{
  private int gameId;
  private String comment;
  private String username;
  private String userImage;
  private String timeStamp;

  public GameComment()
  {
  }

  public GameComment(int gameId, String comment, String username,
      String userImage, String timeStamp)
  {
    this.gameId = gameId;
    this.comment = comment;
    this.username = username;
    this.userImage = userImage;
    this.timeStamp = timeStamp;
  }

  public int getGameId()
  {
    return gameId;
  }

  public void setGameId(int gameId)
  {
    this.gameId = gameId;
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
