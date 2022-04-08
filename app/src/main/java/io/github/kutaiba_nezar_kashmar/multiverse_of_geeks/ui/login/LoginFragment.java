package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment
{
  private FragmentLoginBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    LoginViewModel homeViewModel = new ViewModelProvider(this)
        .get(LoginViewModel.class);

    binding = FragmentLoginBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}
