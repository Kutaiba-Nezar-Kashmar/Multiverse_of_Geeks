package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain;

//TODO: Refactor this class later. For now it is a place holder
public class MovieGenre
{
  private String genreName;
  private int image;


  public MovieGenre(String genreName, int image)
  {
    this.genreName = genreName;
    this.image = image;
  }

  public String getGenreName()
  {
    return genreName;
  }

  public void setGenreName(String genreName)
  {
    this.genreName = genreName;
  }

  public int getImage()
  {
    return image;
  }

  public void setImage(int image)
  {
    this.image = image;
  }
}
