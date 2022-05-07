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

  private MovieReviewRepositoryImpl()
  {
    firebaseDatabase = FirebaseDatabase.getInstance();
    isReviewed = new MutableLiveData<>();
    movieReview = new MutableLiveData<>();
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
        .child(userId);
    review = new MovieReviewLiveData(reference);
  }

  @Override
  public void postReview(MovieReview movieReview)
  {
    reference.setValue(movieReview);
    isReviewed.setValue(true);
  }

  //TODO: avoid duplicate on update and create
  @Override
  public void updateReview(MovieReview movieReview)
  {
    reference.orderByChild("rating").equalTo(String.valueOf(movieReview.getRating()))
        .addListenerForSingleValueEvent(new ValueEventListener()
        {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot)
          {
            if (snapshot.exists())
            {
              isReviewed.setValue(true);
              for (DataSnapshot ds : snapshot.getChildren())
              {
                String key = ds.getKey();
                int movieId = Integer
                    .parseInt(ds.child("movieId").getValue().toString());
                float rating = Float
                    .parseFloat(ds.child("rating").getValue().toString());
                reference.child(key).child("movieId").setValue(movieId);
                reference.child(key).child("rating")
                    .setValue(movieReview.getRating());
              }
            }
            isReviewed.setValue(false);
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error)
          {

          }
        });
  }

  @Override
  public void deleteReview(MovieReview movieReview)
  {

  }

  @Override
  public MovieReviewLiveData getReviews()
  {
    return review;
  }

  @Override
  public LiveData<Boolean> getIsReviewed()
  {
    return isReviewed;
  }
}
