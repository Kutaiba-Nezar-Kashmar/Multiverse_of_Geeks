package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.create_account;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.user.UserRepository;

public class CreateAccountViewModel extends AndroidViewModel
{
  private UserRepository userRepository;
  private MutableLiveData<FirebaseUser> user;

  public CreateAccountViewModel(@NonNull Application application)
  {
    super(application);
    userRepository =  UserRepository.getInstance(application);
    user = userRepository.getCurrentUser();
  }

  public void registerUser(String email, String password)
  {
    userRepository.registerUser(email, password);
  }
}
