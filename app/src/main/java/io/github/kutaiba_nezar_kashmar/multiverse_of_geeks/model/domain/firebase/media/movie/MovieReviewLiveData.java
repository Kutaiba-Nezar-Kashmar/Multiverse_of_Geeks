package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class MovieReviewLiveData extends LiveData<MovieReview>
{
  private final ValueEventListener listener = new ValueEventListener()
  {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot)
    {
      MovieReview review = snapshot.getValue(MovieReview.class);
      setValue(review);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error)
    {

    }
  };

  private DatabaseReference databaseReference;

  public MovieReviewLiveData(DatabaseReference databaseReference)
  {
    this.databaseReference = databaseReference;
  }

  @Override
  protected void onActive()
  {
    super.onActive();
    databaseReference.addValueEventListener(listener);
  }

  @Override
  protected void onInactive()
  {
    super.onInactive();
    databaseReference.removeEventListener(listener);
  }
}
