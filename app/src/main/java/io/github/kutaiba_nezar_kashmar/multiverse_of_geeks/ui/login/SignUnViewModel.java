package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepository;

public class SignUnViewModel extends AndroidViewModel
{
  private final UserRepository userRepository;

  public SignUnViewModel(@NonNull Application application)
  {
    super(application);
    userRepository = UserRepository.getInstance(application);
  }

  public LiveData<FirebaseUser> getCurrentUser()
  {
    return userRepository.getCurrentUser();
  }

  public void signOut()
  {
    userRepository.signOut();
  }
}
