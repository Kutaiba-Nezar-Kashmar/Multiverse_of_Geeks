package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user;

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
}
