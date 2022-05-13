package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.game;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameComment;

public interface GameCommentRepository
{
  /**
   * Add a GameComment object to database
   *
   * @param gameComment The comment to be added
   * @param userId      The id of the comment owner
   */
  void postComment(GameComment gameComment, String userId);

  /**
   * Hold a list of GameComment in A liveData based on given parameter
   *
   * @param gameId The game id that the comments belong to
   * @return LiveData of al list of GameComment object
   */
  LiveData<List<GameComment>> gameComments(int gameId);
}
