package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.media.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie.MovieReview;

public interface MovieReviewRepository
{
  void postReview(MovieReview movieReview, String userId);
  void deleteReview(MovieReview movieReview);
  LiveData<List<MovieReview>> getMovieReviews();
}
