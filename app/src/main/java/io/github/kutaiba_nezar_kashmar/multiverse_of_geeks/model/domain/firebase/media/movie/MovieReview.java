package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie;

public class MovieReview
{
  private float rating;
  private int movieId;

  public MovieReview()
  {
  }

  public MovieReview(float rating, int movieId)
  {
    this.rating = rating;
    this.movieId = movieId;
  }

  public MovieReview(float rating)
  {
    this.rating = rating;
  }

  public float getRating()
  {
    return rating;
  }

  public void setRating(float rating)
  {
    this.rating = rating;
  }

  public int getMovieId()
  {
    return movieId;
  }

  public void setMovieId(int movieId)
  {
    this.movieId = movieId;
  }
}
