package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Commenter;

public class CommentResponse
{
  private String id;
  private String author;
  private Commenter author_details;
  private String content;
  private String created_at;
  private ArrayList<Comment> results = null;

  public Comment getComment()
  {
    return new Comment(id, author, author_details, content, created_at);
  }

  public ArrayList<Comment> getResults()
  {
    return results;
  }
}
