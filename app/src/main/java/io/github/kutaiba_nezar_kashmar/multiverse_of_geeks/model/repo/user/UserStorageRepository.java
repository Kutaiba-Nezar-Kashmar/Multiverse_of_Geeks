package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

public interface UserStorageRepository
{
  void uploadUserProfileImage(Uri path);
  StorageReference getReference();
}
