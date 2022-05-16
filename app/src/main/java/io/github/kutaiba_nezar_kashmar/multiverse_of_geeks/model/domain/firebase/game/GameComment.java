package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game;

import com.google.firebase.storage.StorageReference;

public class GameComment
{
  private int gameId;
  private String userId;
  private String comment;
  private String username;
  private StorageReference userImage = null;
  private String timeStamp;

  public GameComment()
  {
  }

  public GameComment(int gameId, String userId, String comment, String username,
      String timeStamp)
  {
    this.gameId = gameId;
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
