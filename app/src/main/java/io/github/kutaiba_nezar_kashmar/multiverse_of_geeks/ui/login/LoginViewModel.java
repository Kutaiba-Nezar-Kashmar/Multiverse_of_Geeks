package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.user.UserRepository;

public class LoginViewModel extends AndroidViewModel
{
  private UserRepository userRepository;
  private LiveData<FirebaseUser> user;


  public LoginViewModel(@NonNull Application application)
  {
    super(application);
    userRepository =  UserRepository.getInstance(application);
    user = userRepository.getCurrentUser();
  }

  public void login(String email, String password)
  {
    userRepository.login(email, password);
  }
}
