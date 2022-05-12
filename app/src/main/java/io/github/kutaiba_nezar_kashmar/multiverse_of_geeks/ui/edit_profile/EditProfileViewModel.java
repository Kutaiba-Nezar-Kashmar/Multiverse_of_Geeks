package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.edit_profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepositoryImpl;

public class EditProfileViewModel extends AndroidViewModel
{
  private final UserRepository userRepository;

  public EditProfileViewModel(@NonNull Application application)
  {
    super(application);
    userRepository = UserRepositoryImpl.getInstance(application);
  }

  public LiveData<FirebaseUser> getCurrentUser()
  {
    return userRepository.getCurrentUser();
  }
}
