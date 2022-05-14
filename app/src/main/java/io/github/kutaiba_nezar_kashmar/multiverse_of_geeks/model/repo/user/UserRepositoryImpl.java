package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.UserLiveData;

public class UserRepositoryImpl implements UserRepository
{
  private static UserRepository instance;
  private final Application application;
  private final UserLiveData user;

  private UserRepositoryImpl(Application application)
  {
    this.application = application;
    user = new UserLiveData();
  }

  public static synchronized UserRepository getInstance(Application application)
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

  @Override
  public void resetPassword(String email)
  {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    auth.sendPasswordResetEmail(email);
  }

  @Override
  public void updateProfile(Uri imageUri, String name)
  {
    UserProfileChangeRequest.Builder request = new UserProfileChangeRequest.Builder();
    if (Objects.requireNonNull(user.getValue()).getPhotoUrl() != imageUri)
    {
      request.setPhotoUri(imageUri);
    }
    if (!Objects.equals(user.getValue().getDisplayName(), name))
    {
      request.setDisplayName(name);
    }
    user.getValue().updateProfile(request.build());
  }
}
