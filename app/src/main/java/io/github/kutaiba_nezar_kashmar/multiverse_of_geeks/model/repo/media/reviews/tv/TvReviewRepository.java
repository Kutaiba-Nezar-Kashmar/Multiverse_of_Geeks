package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.tv;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv.TvReview;

public interface TvReviewRepository
{
  /**
   * Add a TvReview object to database
   *
   * @param review The review to be added
   * @param userId The id of the review owner
   */
  void postReview(TvReview review, String userId);

  /**
   * Hold a list of TvReview
   *
   * @return LiveData of al list of TvReview object
   */
  LiveData<List<TvReview>> getTvReviews();
}
