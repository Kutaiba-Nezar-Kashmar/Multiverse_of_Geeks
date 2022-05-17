package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.movie;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieComment;

public interface MovieCommentRepository
{
  /**
   * Add a MovieComment object to database
   *
   * @param movieComment The comment to be added
   * @param userId       The id of the comment owner
   */
  void postComment(MovieComment movieComment, String userId);

  /**
   * Hold a list of GameComment in A liveData based on given parameter
   *
   * @param movieId The movie id that the comments belong to
   * @return LiveData of al list of MovieComment object
   */
  LiveData<List<MovieComment>> movieComments(int movieId);
}
