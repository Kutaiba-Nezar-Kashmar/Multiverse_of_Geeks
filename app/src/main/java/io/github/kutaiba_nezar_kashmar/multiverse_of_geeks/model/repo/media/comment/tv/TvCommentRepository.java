package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.tv;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv.TvComment;

public interface TvCommentRepository
{
  /**
   * Add a postComment object to database
   *
   * @param tvComment The comment to be added
   * @param userId    The id of the comment owner
   */
  void postComment(TvComment tvComment, String userId);

  /**
   * Hold a list of TvComment in A liveData based on given parameter
   *
   * @param tvId The tv id that the comments belong to
   * @return LiveData of al list of TvComment object
   */
  LiveData<List<TvComment>> tvComments(int tvId);
}
