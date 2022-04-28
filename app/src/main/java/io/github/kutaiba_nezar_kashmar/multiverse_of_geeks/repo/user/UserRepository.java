package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.user;

import android.app.Application;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.UserLiveData;

public class UserRepository
{
  private FirebaseAuth firebaseAuth;
  private final Application application;
  private final UserLiveData user;
  private static UserRepository instance;

  private UserRepository(Application application)
  {
    this.application = application;
    user = new UserLiveData();
    this.firebaseAuth = FirebaseAuth.getInstance();
  }

  public static synchronized UserRepository getInstance(Application application)
  {
    if (instance == null)
    {
      instance = new UserRepository(application);
    }
    return instance;
  }

  public MutableLiveData<FirebaseUser> getCurrentUser()
  {
    return user;
  }

  public void registerUser(String email, String password)
  {
    firebaseAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(task -> {
          if (task.isSuccessful())
          {
            Toast.makeText(application, "OK", Toast.LENGTH_SHORT).show();
          }
          Toast.makeText(application, "NOT OK", Toast.LENGTH_SHORT).show();
        });
  }
}
