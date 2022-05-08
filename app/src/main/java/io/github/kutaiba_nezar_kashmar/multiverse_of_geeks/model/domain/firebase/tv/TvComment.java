package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv;

public class TvComment
{
  private int tvId;
  private String comment;

  public TvComment()
  {
  }

  public TvComment(int tvId, String comment)
  {
    this.tvId = tvId;
    this.comment = comment;
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
}
