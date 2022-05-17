package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UserStorageRepositoryImpl implements UserStorageRepository
{
  private static UserStorageRepository instance;
  private final StorageReference reference;

  private UserStorageRepositoryImpl()
  {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    reference = storage.getReference("images/");
  }

  public static synchronized UserStorageRepository getInstance()
  {
    if (instance == null)
    {
      instance = new UserStorageRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void uploadUserProfileImage(Uri path, String userId)
  {
    UploadTask uploadTask = reference.child(userId).putFile(path);
    uploadTask.addOnFailureListener(
        e -> Log.i("Firebase Storage: ", "Can't upload")).addOnSuccessListener(
        taskSnapshot -> Log.i("Firebase Storage: ", "Upload done"));
  }

  @Override
  public StorageReference getUserProfileImage(String userId)
  {
    return reference.child(userId);
  }
}
