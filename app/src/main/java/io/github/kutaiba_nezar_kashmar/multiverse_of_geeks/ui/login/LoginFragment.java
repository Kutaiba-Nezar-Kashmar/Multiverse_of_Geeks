package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;

import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment
{
  private FragmentLoginBinding binding;
  private LoginViewModel loginViewModel;
  private EditText emailField;
  private EditText passwordField;
  private Button loginButton;
  private TextView toCreateAccount;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

    binding = FragmentLoginBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    emailField = root.findViewById(R.id.login_email_field);
    passwordField = root.findViewById(R.id.login_password_field);
    loginButton = root.findViewById(R.id.login_button);
    toCreateAccount = root.findViewById(R.id.navigate_to_create_account_link);
    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    loginButton.setOnClickListener(view1 -> {
      login();
    });
    toCreateAccount.setOnClickListener(view1 -> {
      Navigation.findNavController(view)
          .navigate(LoginFragmentDirections.actionNavLoginToNavCreateAccount());
    });
  }

  private void login()
  {
    String email = this.emailField.getText().toString();
    String password = this.passwordField.getText().toString();
    if (!email.isEmpty() && !password.isEmpty())
    {
      loginViewModel.login(email, password);
    }
    else
    {
      Toast.makeText(getContext(), "email and password are required",
          Toast.LENGTH_SHORT).show();
    }
  }
}
