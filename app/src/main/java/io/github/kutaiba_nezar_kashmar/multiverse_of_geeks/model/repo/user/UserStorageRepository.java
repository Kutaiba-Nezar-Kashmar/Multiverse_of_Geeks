package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

public interface UserStorageRepository
{
  /**
   * Upload the user's profile image to cloud service based on given parameters
   *
   * @param path   The name space of the object's parent
   * @param userId The user's id which will be the name of the saved image
   */
  void uploadUserProfileImage(Uri path, String userId);

  /**
   * Retrieve the user's profile image based on the given parameter
   *
   * @param userId The name of the file to be retrieved
   * @return the cloud reference where the image is store at
   */
  StorageReference getUserProfileImage(String userId);
}
