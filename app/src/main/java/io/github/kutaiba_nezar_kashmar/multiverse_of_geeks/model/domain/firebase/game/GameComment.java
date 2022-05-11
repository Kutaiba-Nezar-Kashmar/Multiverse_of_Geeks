package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game;

public class GameComment
{
  private int gameId;
  private String comment;

  public GameComment()
  {
  }

  public GameComment(int gameId, String comment)
  {
    this.gameId = gameId;
    this.comment = comment;
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
}
