package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.user;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

  public LiveData<FirebaseUser> getCurrentUser()
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
          if (email.isEmpty())
          {
            Toast.makeText(application, "Email must be provided",
                Toast.LENGTH_SHORT).show();
          }
          if (!email
              .matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
          {
            Toast.makeText(application, "Invalid email", Toast.LENGTH_SHORT)
                .show();
          }
          if (password.isEmpty())
          {
            Toast.makeText(application, "Password must be provided",
                Toast.LENGTH_SHORT).show();
          }
          if (password.length() < 6)
          {
            Toast.makeText(application, "Password most be at least 6 digits",
                Toast.LENGTH_SHORT).show();
          }
          else
          {
            Toast.makeText(application, "NOT OK", Toast.LENGTH_SHORT).show();
          }
        });
  }

  public void login(String email, String password)
  {
    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
      if (task.isSuccessful())
      {
        Toast.makeText(application, "Ok" , Toast.LENGTH_SHORT).show();
      }
      if (email.isEmpty())
      {
        Toast.makeText(application, "Email must be provided",
            Toast.LENGTH_SHORT).show();
      }
      if (!email
          .matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
      {
        Toast.makeText(application, "Invalid email", Toast.LENGTH_SHORT)
            .show();
      }
      if (password.isEmpty())
      {
        Toast.makeText(application, "Password must be provided",
            Toast.LENGTH_SHORT).show();
      }
      if (password.length() < 6)
      {
        Toast.makeText(application, "Password most be at least 6 digits",
            Toast.LENGTH_SHORT).show();
      }
      else
      {
        Toast.makeText(application, "NOT OK", Toast.LENGTH_SHORT).show();
      }
    });
  }

  public void signOut()
  {
    firebaseAuth.signOut();
  }
}
