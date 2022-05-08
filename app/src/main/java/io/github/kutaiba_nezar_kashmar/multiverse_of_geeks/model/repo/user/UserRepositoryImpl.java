package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.UserLiveData;

public class UserRepositoryImpl implements UserRepository
{
  private static UserRepository instance;
  private FirebaseAuth firebaseAuth;
  private final Application application;
  private final UserLiveData user;

  private UserRepositoryImpl(Application application)
  {
    this.application = application;
    user = new UserLiveData();
    this.firebaseAuth = FirebaseAuth.getInstance();
  }

  public static synchronized UserRepository getInstance(
      Application application)
  {
    if (instance == null)
    {
      instance = new UserRepositoryImpl(application);
    }
    return instance;
  }

  @Override
  public LiveData<FirebaseUser> getCurrentUser()
  {
    return user;
  }

  @Override
  public void signOut()
  {
    AuthUI.getInstance().signOut(application.getApplicationContext());
  }
}
