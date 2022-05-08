package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.media.movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie.MovieReview;

public class MovieReviewRepositoryImpl implements MovieReviewRepository
{
  private static MovieReviewRepositoryImpl instance;
  private final FirebaseDatabase firebaseDatabase;
  private DatabaseReference reference;
  private final MutableLiveData<Boolean> isReviewed;
  private final MutableLiveData<MovieReview> movieReview;
  private final MutableLiveData<Float> averageReviews;
  private final MutableLiveData<List<MovieReview>> movieReviews;

  private MovieReviewRepositoryImpl()
  {
    firebaseDatabase = FirebaseDatabase.getInstance();
    reference = firebaseDatabase.getReference();
    isReviewed = new MutableLiveData<>();
    movieReview = new MutableLiveData<>();
    averageReviews = new MutableLiveData<>();
    movieReviews = new MutableLiveData<>();
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
  public void postReview(MovieReview movieReview, String userId)
  {
    reference.child("users").child(userId)
        .child("movieReview" + movieReview.getMovieId()).setValue(movieReview);
    isReviewed.setValue(true);
  }

  @Override
  public void deleteReview(MovieReview movieReview)
  {

  }

  @Override
  public LiveData<List<MovieReview>> getMovieReviews()
  {
    reference.child("users");
    List<MovieReview> reviewList = new ArrayList<>();
    ValueEventListener listener = new ValueEventListener()
    {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot)
      {
        Float average = 0.0F;
        for (DataSnapshot ds : snapshot.getChildren())
        {
          for (DataSnapshot dss : ds.getChildren())
          {
            for (DataSnapshot dsst : dss.getChildren())
            {
              MovieReview movieReview = dsst.getValue(MovieReview.class);
              reviewList.add(movieReview);
            }
          }
        }
        movieReviews.postValue(reviewList);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error)
      {

      }
    };
    reference.addListenerForSingleValueEvent(listener);
    return movieReviews;
  }
}
