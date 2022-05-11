package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game;

public class GameReview
{
  private int gameId;
  private float gameRating;

  public GameReview()
  {
  }

  public GameReview(int gameId, float gameRating)
  {
    this.gameId = gameId;
    this.gameRating = gameRating;
  }

  public int getGameId()
  {
    return gameId;
  }

  public void setGameId(int gameId)
  {
    this.gameId = gameId;
  }

  public float getGameRating()
  {
    return gameRating;
  }

  public void setGameRating(float gameRating)
  {
    this.gameRating = gameRating;
  }
}
