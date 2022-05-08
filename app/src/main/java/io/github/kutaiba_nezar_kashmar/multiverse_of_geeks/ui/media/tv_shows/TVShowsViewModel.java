package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.MovieReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.TvReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.media.tv.TvReviewRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.media.tv.TvReviewRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.tv_show.TVShowRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.tv_show.TvShowRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepositoryImpl;

public class TVShowsViewModel extends AndroidViewModel
{
  private TvShowRepository tvShowRepository;
  private UserRepository userRepository;
  private TvReviewRepository tvReviewRepository;

  public TVShowsViewModel(@NonNull Application application)
  {
    super(application);
    tvShowRepository = TVShowRepositoryImpl.getInstance(application);
    userRepository = UserRepositoryImpl.getInstance(application);
    tvReviewRepository = TvReviewRepositoryImpl.getInstance();
  }

  public LiveData<FirebaseUser> getCurrentUser()
  {
    return userRepository.getCurrentUser();
  }

  public void postReview(TvReview tvReview)
  {
    String userId = userRepository.getCurrentUser().getValue().getUid();
    tvReviewRepository.postReview(tvReview, userId);
  }

  LiveData<List<TvReview>> getTvReviews()
  {
    return tvReviewRepository.getTvReviews();
  }

  public float calculateAverage(List<TvReview> tvReviews)
  {
    float total = 0;
    for (TvReview mr : tvReviews)
    {
      total += mr.getRating();
    }
    return total / tvReviews.size();
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

  public LiveData<List<Comment>> getAllComments(int id)
  {
    return tvShowRepository.getTvReviews(id);
  }
}
