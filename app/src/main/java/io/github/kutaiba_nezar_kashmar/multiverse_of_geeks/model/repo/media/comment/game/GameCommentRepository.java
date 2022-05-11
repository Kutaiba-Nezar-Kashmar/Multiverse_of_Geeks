package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.game;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameComment;

public interface GameCommentRepository
{
  void postComment(GameComment gameComment, String userId);
  void deleteComment(GameComment gameComment);
  LiveData<List<GameComment>> gameComments(int gameId);
}
