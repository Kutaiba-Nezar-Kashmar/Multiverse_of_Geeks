package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie;

public class MovieReview
{
  private float rating;
  private int movieId;
  private boolean isReviewed;

  public MovieReview()
  {
  }

  public MovieReview(float rating, int movieId, boolean isReviewed)
  {
    this.rating = rating;
    this.movieId = movieId;
    this.isReviewed = isReviewed;
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

  public boolean isReviewed()
  {
    return isReviewed;
  }

  public void setReviewed(boolean reviewed)
  {
    isReviewed = reviewed;
  }
}
