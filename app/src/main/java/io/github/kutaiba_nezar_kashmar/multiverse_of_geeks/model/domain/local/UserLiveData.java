package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserLiveData extends LiveData<FirebaseUser>
{
  private final FirebaseAuth.AuthStateListener listener = firebaseAuth -> setValue(
      firebaseAuth.getCurrentUser());

  @Override
  protected void onActive()
  {
    super.onActive();
    FirebaseAuth.getInstance().addAuthStateListener(listener);
  }

  @Override
  protected void onInactive()
  {
    super.onInactive();
    FirebaseAuth.getInstance().removeAuthStateListener(listener);
  }
}
