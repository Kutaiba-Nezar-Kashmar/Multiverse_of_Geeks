package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.game;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.GameReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.MovieReview;

public class GameReviewRepositoryImpl implements GameReviewRepository
{
  private static GameReviewRepository instance;
  private final FirebaseDatabase firebaseDatabase;
  private DatabaseReference reference;
  private final MutableLiveData<GameReview> myGameReview;
  private final MutableLiveData<List<GameReview>> gameReviews;

  private GameReviewRepositoryImpl()
  {
    firebaseDatabase = FirebaseDatabase.getInstance();
    reference = firebaseDatabase.getReference();
    gameReviews = new MutableLiveData<>();
    myGameReview = new MutableLiveData<>();
  }

  public static synchronized GameReviewRepository getInstance()
  {
    if (instance == null)
    {
      instance = new GameReviewRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void postReview(GameReview gameReview, String userId)
  {
    reference.child("users").child(userId)
        .child("gameReview" + gameReview.getGameId()).setValue(gameReview);
  }

  @Override
  public void deleteReview(GameReview gameReview)
  {

  }

  @Override
  public LiveData<List<GameReview>> getGameReviews()
  {
    reference.child("users");
    List<GameReview> reviewList = new ArrayList<>();
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
              GameReview gameReview = dsst.getValue(GameReview.class);
              reviewList.add(gameReview);
            }
          }
        }
        gameReviews.postValue(reviewList);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error)
      {

      }
    };
    reference.addListenerForSingleValueEvent(listener);
    return gameReviews;
  }
}
