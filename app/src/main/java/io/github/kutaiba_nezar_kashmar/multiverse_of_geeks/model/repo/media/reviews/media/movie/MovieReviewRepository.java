package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.media.movie;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie.MovieReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie.MovieReviewLiveData;

public interface MovieReviewRepository
{
  void init(String userId);
  void postReview(MovieReview movieReview);
  MovieReviewLiveData getReviews();
}
