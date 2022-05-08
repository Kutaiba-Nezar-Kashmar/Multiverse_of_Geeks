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

  private MovieReviewRepositoryImpl()
  {
    firebaseDatabase = FirebaseDatabase.getInstance();
    reference = firebaseDatabase.getReference();
    isReviewed = new MutableLiveData<>();
    movieReview = new MutableLiveData<>();
    averageReviews = new MutableLiveData<>();
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

  //TODO: Find a way to return results for only one movie
  @Override
  public LiveData<Float> getReviews(int movieId)
  {
    reference.child("users");
    ValueEventListener listener = new ValueEventListener()
    {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot)
      {
        List<Float> ratings = new ArrayList<>();
        List<String> users = new ArrayList<>();
        Float average = 0.0F;
        for (DataSnapshot ds : snapshot.getChildren())
        {
          for (DataSnapshot dss : ds.getChildren())
          {
            for (DataSnapshot dsst : dss.getChildren())
            {
              Float rating = dsst.child("rating").getValue(Float.class);
              ratings.add(rating);
            }
          }
        }
        float sum = 0;
        for (Float f : ratings)
        {
          sum += f;
        }
        average = (sum / ratings.size() * 2);
        averageReviews.postValue(average);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error)
      {

      }
    };
    reference.addListenerForSingleValueEvent(listener);
    return averageReviews;
  }
}
