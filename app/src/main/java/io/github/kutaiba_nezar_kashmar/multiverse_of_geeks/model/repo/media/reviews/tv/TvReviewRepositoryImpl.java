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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.TvReview;

public class TvReviewRepositoryImpl implements TvReviewRepository
{
  private static TvReviewRepository instance;
  private final FirebaseDatabase firebaseDatabase;
  private DatabaseReference reference;
  private final MutableLiveData<TvReview> myTvReview;
  private final MutableLiveData<List<TvReview>> tvReviews;

  private TvReviewRepositoryImpl()
  {
    firebaseDatabase = FirebaseDatabase.getInstance();
    reference = firebaseDatabase.getReference();
    tvReviews = new MutableLiveData<>();
    myTvReview = new MutableLiveData<>();
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
    reference.child("users").child(userId).child("tvReviews" + review.getTvId())
        .setValue(review);
  }

  @Override
  public void deleteReview(TvReview tvReview)
  {

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
        for (DataSnapshot ds : snapshot.getChildren())
        {
          for (DataSnapshot dss : ds.getChildren())
          {
            for (DataSnapshot dsst : dss.getChildren())
            {
              TvReview tvReview = dsst.getValue(TvReview.class);
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
