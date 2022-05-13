package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.tv;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv.TvReview;

public class TvReviewRepositoryImpl implements TvReviewRepository
{
  private static TvReviewRepository instance;
  private final DatabaseReference reference;
  private final MutableLiveData<List<TvReview>> tvReviews;

  private TvReviewRepositoryImpl()
  {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    reference = firebaseDatabase.getReference().child("tvReview");
    tvReviews = new MutableLiveData<>();
  }

  public static synchronized TvReviewRepository getInstance()
  {
    if (instance == null)
    {
      instance = new TvReviewRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void postReview(TvReview review, String userId)
  {
    reference.child("users").child(userId)
        .child(String.valueOf(review.getTvId())).setValue(review);
  }

  @Override
  public LiveData<List<TvReview>> getTvReviews()
  {
    reference.child("users");
    List<TvReview> reviewList = new ArrayList<>();
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
              TvReview tvReview = dss.getValue(TvReview.class);
              reviewList.add(tvReview);
            }
          }
        }

        tvReviews.postValue(reviewList);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error)
      {

      }
    };
    reference.addListenerForSingleValueEvent(listener);
    return tvReviews;
  }
}
