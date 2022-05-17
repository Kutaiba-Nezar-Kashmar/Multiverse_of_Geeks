package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.Objects;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv.TvComment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv.TvReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.tv.TvCommentRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.tv.TvCommentRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.tv.TvReviewRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.tv.TvReviewRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.tv_show.TVShowRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.tv_show.TvShowRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserStorageRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserStorageRepositoryImpl;

public class TVShowsViewModel extends AndroidViewModel
{
  private final TvShowRepository tvShowRepository;
  private final UserRepository userRepository;
  private final TvReviewRepository tvReviewRepository;
  private final TvCommentRepository tvCommentRepository;
  private final UserStorageRepository userStorageRepository;

  public TVShowsViewModel(@NonNull Application application)
  {
    super(application);
    tvShowRepository = TVShowRepositoryImpl.getInstance(application);
    userRepository = UserRepositoryImpl.getInstance(application);
    tvReviewRepository = TvReviewRepositoryImpl.getInstance();
    tvCommentRepository = TvCommentRepositoryImpl.getInstance();
    userStorageRepository = UserStorageRepositoryImpl.getInstance();
  }

  public LiveData<FirebaseUser> getCurrentUser()
  {
    return userRepository.getCurrentUser();
  }

  public StorageReference getProfileImage(String userId)
  {
    return userStorageRepository.getUserProfileImage(userId);
  }

  public void postReview(TvReview tvReview)
  {
    String userId = Objects.requireNonNull(
        userRepository.getCurrentUser().getValue()).getUid();
    tvReviewRepository.postReview(tvReview, userId);
  }

  LiveData<List<TvReview>> getTvReviews()
  {
    return tvReviewRepository.getTvReviews();
  }

  public float calculateAverage(List<TvReview> tvReviews)
  {
    //Get all reviews for a Tv show and find average for them
    float total = 0;
    for (TvReview mr : tvReviews)
    {
      total += mr.getRating();
    }
    return total / tvReviews.size();
  }

  public void postComment(TvComment tvComment)
  {
    String userId = Objects.requireNonNull(
        userRepository.getCurrentUser().getValue()).getUid();
    tvCommentRepository.postComment(tvComment, userId);
  }

  public LiveData<List<TvComment>> getTvComments(int tvId)
  {
    return tvCommentRepository.tvComments(tvId);
  }

  public LiveData<List<SingleTvShowResponse>> getFavoriteTvShows()
  {
    return tvShowRepository.getFavoriteTvShows();
  }

  public LiveData<SingleTvShowResponse> getSingleFavoriteTvShow(int id)
  {
    return tvShowRepository.getSingleFavoriteTvShow(id);
  }

  public void insertFavoriteTvShow(SingleTvShowResponse tv)
  {
    tvShowRepository.insertFavoriteTvShow(tv);
  }

  public void deleteFavoriteTvShow(SingleTvShowResponse tv)
  {
    tvShowRepository.deleteFavoriteTvShow(tv);
  }

  public LiveData<SingleTvShowResponse> findTvShowById(int id)
  {
    return tvShowRepository.findTvShowById(id);
  }

  public LiveData<List<TvShow>> getAllPopularTvShows(int pageNumber)
  {
    return tvShowRepository.getAllPopularTvShows(pageNumber);
  }

  public LiveData<List<TvShow>> getAllTopRatedTvShows(int pageNumber)
  {
    return tvShowRepository.getAllTopRatedTvShows(pageNumber);
  }

  public LiveData<List<TvShow>> getAllOnAirTvShows(int pageNumber)
  {
    return tvShowRepository.getAllOnAirTvShows(pageNumber);
  }

  public LiveData<List<TvShow>> getAllAiringTodayTvShows(int pageNumber)
  {
    return tvShowRepository.getAllAiringTodayTvShows(pageNumber);
  }

  public LiveData<List<TvShow>> getAllSearchedTvShows(String arg)
  {
    return tvShowRepository.getAllSearchedTvShows(arg);
  }
}
