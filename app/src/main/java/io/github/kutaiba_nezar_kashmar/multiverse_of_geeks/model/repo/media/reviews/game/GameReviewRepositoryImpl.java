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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameReview;

public class GameReviewRepositoryImpl implements GameReviewRepository
{
  private static GameReviewRepository instance;
  private final DatabaseReference reference;
  private final MutableLiveData<List<GameReview>> gameReviews;

  private GameReviewRepositoryImpl()
  {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    reference = firebaseDatabase.getReference().child("gameReview");
    gameReviews = new MutableLiveData<>();
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
        .child(String.valueOf(gameReview.getGameId())).setValue(gameReview);
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
        for (DataSnapshot d : snapshot.getChildren())
        {
          for (DataSnapshot ds : d.getChildren())
          {
            for (DataSnapshot dss : ds.getChildren())
            {
              GameReview gameReview = dss.getValue(GameReview.class);
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
