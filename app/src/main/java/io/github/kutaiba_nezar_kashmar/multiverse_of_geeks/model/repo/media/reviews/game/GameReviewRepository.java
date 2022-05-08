package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.game;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.GameReview;

public interface GameReviewRepository
{
  void postReview(GameReview gameReview, String userId);
  void deleteReview(GameReview gameReview);
  LiveData<List<GameReview>> getGameReviews();
}
