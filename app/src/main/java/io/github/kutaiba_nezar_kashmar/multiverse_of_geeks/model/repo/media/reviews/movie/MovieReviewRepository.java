package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.movie;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieReview;

public interface MovieReviewRepository
{
  /**
   * Add a MovieReview object to database
   *
   * @param movieReview The review to be added
   * @param userId      The id of the review owner
   */
  void postReview(MovieReview movieReview, String userId);

  /**
   * Hold a list of MovieReview
   *
   * @return LiveData of al list of MovieReview object
   */
  LiveData<List<MovieReview>> getMovieReviews();
}
