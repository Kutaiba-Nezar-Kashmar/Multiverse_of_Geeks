package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;

public interface UserRepository
{
  LiveData<FirebaseUser> getCurrentUser();
  void signOut();
}
