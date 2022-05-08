package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv;

public class TvReview
{
  private float rating;
  private int tvId;

  public TvReview()
  {
  }

  public TvReview(float rating, int tvId)
  {
    this.rating = rating;
    this.tvId = tvId;
  }

  public float getRating()
  {
    return rating;
  }

  public void setRating(float rating)
  {
    this.rating = rating;
  }

  public int getTvId()
  {
    return tvId;
  }

  public void setTvId(int tvId)
  {
    this.tvId = tvId;
  }
}
