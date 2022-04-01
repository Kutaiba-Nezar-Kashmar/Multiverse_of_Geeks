package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain;

public class Movie
{
  private final String title;
  private final String genre;
  private final String targetAudience;
  private final int yearOfRelease;
  private final String description;
  private final int numberOfCast;
  private final String imageUri;

  public Movie(String title, String genre, String targetAudience,
      int yearOfRelease, String description, int numberOfCast, String imageUri)
  {
    this.title = title;
    this.genre = genre;
    this.targetAudience = targetAudience;
    this.yearOfRelease = yearOfRelease;
    this.description = description;
    this.numberOfCast = numberOfCast;
    this.imageUri = imageUri;
  }

  public String getTitle()
  {
    return title;
  }

  public String getGenre()
  {
    return genre;
  }

  public String getTargetAudience()
  {
    return targetAudience;
  }

  public int getYearOfRelease()
  {
    return yearOfRelease;
  }

  public String getDescription()
  {
    return description;
  }

  public int getNumberOfCast()
  {
    return numberOfCast;
  }

  public String getImageUri()
  {
    return imageUri;
  }
}
