package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.my_profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserStorageRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserStorageRepositoryImpl;

public class MyProfileViewModel extends AndroidViewModel
{
  private final UserRepository userRepository;
  private UserStorageRepository userStorageRepository;

  public MyProfileViewModel(@NonNull Application application)
  {
    super(application);
    userRepository = UserRepositoryImpl.getInstance(application);
  }

  public LiveData<FirebaseUser> getCurrentUser()
  {
    return userRepository.getCurrentUser();
  }

  public void setUserStorageRepository(String userId)
  {
    userStorageRepository = UserStorageRepositoryImpl.getInstance(userId);
  }

  public void signOut()
  {
    userRepository.signOut();
  }

  public StorageReference profileImagePath()
  {
    return userStorageRepository.getReference();
  }
}
