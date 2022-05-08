package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.media.tv;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.TvReview;

public interface TvReviewRepository
{
  void postReview(TvReview review, String userId);
  void deleteReview(TvReview tvReview);
  LiveData<List<TvReview>> getTvReviews();
}
