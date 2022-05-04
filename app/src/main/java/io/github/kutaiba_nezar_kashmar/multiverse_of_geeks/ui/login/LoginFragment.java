package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login;

import android.content.Intent;
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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.MainActivity;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment
{
  private FragmentLoginBinding binding;
  private SignUnViewModel signUnViewModel;
  private Button signOut;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    signUnViewModel = new ViewModelProvider(this).get(SignUnViewModel.class);

    binding = FragmentLoginBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    signOut = root.findViewById(R.id.logout);
    checkIfSignedIn();
    signOut.setOnClickListener(this::signOut);
    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  private void checkIfSignedIn()
  {
    signUnViewModel.getCurrentUser()
        .observe(getViewLifecycleOwner(), firebaseUser -> {
          if (firebaseUser != null)
          {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
          }
          else
          {
            Intent intent = new Intent(getActivity(), SignInActivity.class);
            startActivity(intent);
          }
        });
  }

  private void signOut(View view)
  {
    signUnViewModel.signOut();
  }
}
