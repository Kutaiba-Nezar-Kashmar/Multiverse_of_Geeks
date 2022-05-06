package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.MainActivity;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class SignInActivity extends AppCompatActivity
{
  private SignUnViewModel signUnViewModel;

  ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
      new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK)
        {
          goToMainActivity();
        }
        else
        {
          System.out.println(
              "++++++++++++++++++++++++++++++++++++++++++++++++++++ " + result
                  .getResultCode());
          Toast.makeText(this, "SIGN IN CANCELLED", Toast.LENGTH_SHORT).show();
        }
      });

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    signUnViewModel = new ViewModelProvider(this).get(SignUnViewModel.class);
    checkIfSignedIn();
    setContentView(R.layout.signin_activity);
  }

  private void checkIfSignedIn()
  {
    signUnViewModel.getCurrentUser().observe(this, firebaseUser -> {
      if (firebaseUser != null)
      {
        goToMainActivity();
      }
      else
      {
        signIn();
      }
    });
  }

  private void goToMainActivity()
  {
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }

  public void signIn()
  {
    List<AuthUI.IdpConfig> providers = Arrays
        .asList(new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build());

    Intent signInIntent = AuthUI.getInstance().createSignInIntentBuilder()
        .setAvailableProviders(providers).setIsSmartLockEnabled(false)
        .setLogo(R.mipmap.my_logo_round).build();

    activityResultLauncher.launch(signInIntent);
  }
}
