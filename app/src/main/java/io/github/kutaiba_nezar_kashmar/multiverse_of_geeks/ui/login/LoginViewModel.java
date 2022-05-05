package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepositoryImpl;

public class LoginViewModel extends AndroidViewModel
{
  private UserRepository userRepository;


  public LoginViewModel(@NonNull Application application)
  {
    super(application);
    userRepository =  UserRepositoryImpl.getInstance(application);
  }
}
