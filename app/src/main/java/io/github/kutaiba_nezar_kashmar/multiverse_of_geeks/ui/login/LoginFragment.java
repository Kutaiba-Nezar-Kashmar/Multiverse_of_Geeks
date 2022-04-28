package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment
{
  private FragmentLoginBinding binding;
  private EditText emailField;
  private EditText passwordField;
  private Button loginButton;
  private FirebaseAuth firebaseAuth;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    LoginViewModel homeViewModel = new ViewModelProvider(this)
        .get(LoginViewModel.class);

    binding = FragmentLoginBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    emailField = root.findViewById(R.id.login_email_field);
    passwordField = root.findViewById(R.id.login_email_field);
    loginButton = root.findViewById(R.id.login_button);
    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}
