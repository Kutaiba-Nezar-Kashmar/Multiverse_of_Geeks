package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.media.movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie.MovieReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie.MovieReviewLiveData;

public class MovieReviewRepositoryImpl implements MovieReviewRepository
{
  private static MovieReviewRepositoryImpl instance;
  private final FirebaseDatabase firebaseDatabase;
  private DatabaseReference reference;
  private MovieReviewLiveData review;
  private final MutableLiveData<Boolean> isReviewed;
  private final MutableLiveData<MovieReview> movieReview;
  private final MutableLiveData<Float> average;

  private MovieReviewRepositoryImpl()
  {
    firebaseDatabase = FirebaseDatabase.getInstance();
    isReviewed = new MutableLiveData<>();
    movieReview = new MutableLiveData<>();
    average = new MutableLiveData<>();
  }

  public static synchronized MovieReviewRepositoryImpl getInstance()
  {
    if (instance == null)
    {
      instance = new MovieReviewRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void init(String userId)
  {
    reference = firebaseDatabase.getReference().child("users")
        .child(userId).child("movieReview");
  }

  @Override
  public void postReview(MovieReview movieReview)
  {
    reference.child("movieReview" + movieReview.getMovieId()).setValue(movieReview);
    isReviewed.setValue(true);
  }

  //TODO: avoid duplicate on update and create

  @Override
  public void deleteReview(MovieReview movieReview)
  {

  }

  @Override
  public MovieReviewLiveData getReviews(int movieId)
  {
    review = new MovieReviewLiveData(reference.child("movieReview" + movieId));
    return review;
  }


  @Override
  public LiveData<Float> getAverageRating(int MovieId)
  {
    return null;
  }
}
