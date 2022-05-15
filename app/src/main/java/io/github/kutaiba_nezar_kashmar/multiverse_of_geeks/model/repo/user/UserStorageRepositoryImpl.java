package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class UserStorageRepositoryImpl implements UserStorageRepository
{
  private static UserStorageRepository instance;
  private FirebaseStorage storage;
  private StorageReference reference;

  private UserStorageRepositoryImpl(String userId)
  {
    storage = FirebaseStorage.getInstance();
    reference = storage.getReference("images/" + userId);
  }

  public static synchronized UserStorageRepository getInstance(String userId)
  {
    if (instance == null)
    {
      instance = new UserStorageRepositoryImpl(userId);
    }
    return instance;
  }

  @Override
  public void uploadUserProfileImage(Uri path)
  {
    //Uri file = Uri.fromFile(new File(path));
    UploadTask uploadTask = reference.putFile(path);
    uploadTask.addOnFailureListener(
        e -> Log.i("Firebase Storage: ", "Can't upload")).addOnSuccessListener(
        taskSnapshot -> Log.i("Firebase Storage: ", "Upload done"));
  }
}
