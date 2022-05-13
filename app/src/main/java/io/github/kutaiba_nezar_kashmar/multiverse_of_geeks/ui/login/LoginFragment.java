package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.MainActivity;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment
{
  private FragmentLoginBinding binding;
  private SignUnViewModel signUnViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    signUnViewModel = new ViewModelProvider(this).get(SignUnViewModel.class);

    binding = FragmentLoginBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    checkIfSignedIn();
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
          Intent intent;
          if (firebaseUser != null)
          {
            intent = new Intent(getActivity(), MainActivity.class);
          }
          else
          {
            intent = new Intent(getActivity(), SignInActivity.class);
          }
          startActivity(intent);
        });
  }
}
