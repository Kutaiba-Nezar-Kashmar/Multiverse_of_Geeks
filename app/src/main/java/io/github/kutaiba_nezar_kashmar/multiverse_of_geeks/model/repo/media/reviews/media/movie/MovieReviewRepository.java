package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.media.movie;

import androidx.lifecycle.LiveData;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie.MovieReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie.MovieReviewLiveData;

public interface MovieReviewRepository
{
  void init(String userId);
  void postReview(MovieReview movieReview);
  void deleteReview(MovieReview movieReview);
  MovieReviewLiveData getReviews(int movieId);
  LiveData<Float> getAverageRating(int MovieId);
}
