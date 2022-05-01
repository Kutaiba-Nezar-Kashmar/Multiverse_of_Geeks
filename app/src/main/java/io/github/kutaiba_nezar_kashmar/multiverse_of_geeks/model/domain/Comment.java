package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain;

public class Comment
{
  private String id;
  private String author;
  private Commenter author_details;
  private String content;
  private String created_at;

  public Comment(String id, String author, Commenter author_details,
      String content, String created_at)
  {
    this.id = id;
    this.author = author;
    this.author_details = author_details;
    this.content = content;
    this.created_at = created_at;
  }

  public String getId()
  {
    return id;
  }

  public String getAuthor()
  {
    return author;
  }

  public Commenter getAuthor_details()
  {
    return author_details;
  }

  public String getContent()
  {
    return content;
  }

  public String getCreated_at()
  {
    return created_at;
  }
}
