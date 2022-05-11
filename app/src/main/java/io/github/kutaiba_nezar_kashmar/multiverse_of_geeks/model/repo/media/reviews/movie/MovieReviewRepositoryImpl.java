package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.movie;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieReview;

public class MovieReviewRepositoryImpl implements MovieReviewRepository
{
  private static MovieReviewRepository instance;
  private final FirebaseDatabase firebaseDatabase;
  private DatabaseReference reference;
  private final MutableLiveData<MovieReview> myMovieReview;
  private final MutableLiveData<List<MovieReview>> movieReviews;

  private MovieReviewRepositoryImpl()
  {
    firebaseDatabase = FirebaseDatabase.getInstance();
    reference = firebaseDatabase.getReference().child("movieReview");
    movieReviews = new MutableLiveData<>();
    myMovieReview = new MutableLiveData<>();
  }

  public static synchronized MovieReviewRepository getInstance()
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
        .child(String.valueOf(movieReview.getMovieId())).setValue(movieReview);
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
        for (DataSnapshot d : snapshot.getChildren())
        {
          for (DataSnapshot ds : d.getChildren())
          {
            for (DataSnapshot dss : ds.getChildren())
            {
              MovieReview movieReview = dss.getValue(MovieReview.class);
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
