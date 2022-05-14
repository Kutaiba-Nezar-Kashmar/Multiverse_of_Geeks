package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user;

import android.net.Uri;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

public interface UserRepository
{
  /**
   * Hold the currently logged in user
   *
   * @return LiveData that holds the current logged in FirebaseUser
   */
  LiveData<FirebaseUser> getCurrentUser();

  /**
   * Sign out user
   */
  void signOut();

  /**
   * reset password
   */
  void resetPassword(String email);

  /**
   * Update user's profile image and name
   *
   * @param imageUri The new image uri
   * @param name     The new user's name
   */
  void updateProfile(Uri imageUri, String name);
}
