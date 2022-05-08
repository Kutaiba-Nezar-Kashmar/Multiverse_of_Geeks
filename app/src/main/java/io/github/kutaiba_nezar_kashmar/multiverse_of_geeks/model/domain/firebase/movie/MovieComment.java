package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie;

public class MovieComment
{
  private int movieId;
  private String comment;

  public MovieComment()
  {
  }

  public MovieComment(int movieId, String comment)
  {
    this.movieId = movieId;
    this.comment = comment;
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
}
