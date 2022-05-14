package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.edit_profile;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepositoryImpl;

public class EditProfileViewModel extends AndroidViewModel
{
  private final UserRepository userRepository;
  private String notification;

  public EditProfileViewModel(@NonNull Application application)
  {
    super(application);
    userRepository = UserRepositoryImpl.getInstance(application);
  }

  public LiveData<FirebaseUser> getCurrentUser()
  {
    return userRepository.getCurrentUser();
  }

  public void deleteAccount()
  {
    Objects.requireNonNull(userRepository.getCurrentUser().getValue()).delete()
        .addOnCompleteListener(task -> {
          if (task.isSuccessful())
          {
            setNotification("Account deleted");
          }
          setNotification("Cant delete account");
        });
  }

  public void resetPassword()
  {
    String email = Objects.requireNonNull(
        userRepository.getCurrentUser().getValue()).getEmail();
    userRepository.resetPassword(email);
  }

  public String getNotification()
  {
    return notification;
  }

  private void setNotification(String notification)
  {
    this.notification = notification;
  }

  public void updateProfile(Uri imageUri, String name)
  {
    userRepository.updateProfile(imageUri, name);
  }
}
