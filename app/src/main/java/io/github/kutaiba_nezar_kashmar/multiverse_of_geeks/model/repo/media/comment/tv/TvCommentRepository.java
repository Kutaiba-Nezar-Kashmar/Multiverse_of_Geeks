package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.tv;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv.TvComment;

public interface TvCommentRepository
{
  void postComment(TvComment tvComment, String userId);
  void deleteComment(TvComment tvComment);
  LiveData<List<TvComment>> tvComments(int tvId);
}
