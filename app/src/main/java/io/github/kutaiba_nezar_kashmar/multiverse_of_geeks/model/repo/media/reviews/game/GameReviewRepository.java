package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.game;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameReview;

public interface GameReviewRepository
{
  /**
   * Add a GameReview object to database
   *
   * @param gameReview The review to be added
   * @param userId     The id of the review owner
   */
  void postReview(GameReview gameReview, String userId);

  /**
   * Hold a list of GameReview
   *
   * @return LiveData of al list of GameReview object
   */
  LiveData<List<GameReview>> getGameReviews();
}
