package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.movie;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.Query;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieComment;

public interface MovieCommentRepository
{
  void postComment(MovieComment movieComment, String userId);
  void deleteComment(MovieComment movieComment);
  LiveData<List<MovieComment>> movieComments(int movieId);
}
