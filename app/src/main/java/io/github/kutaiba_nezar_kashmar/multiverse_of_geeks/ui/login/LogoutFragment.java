package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentLogoutBinding;

public class LogoutFragment extends Fragment
{
  private FragmentLogoutBinding binding;
  private LoginViewModel loginViewModel;
  private Button button;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    binding = FragmentLogoutBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    button = root.findViewById(R.id.logout_button_id);
    return root;
  }

  @Override
  public void onDestroy()
  {
    super.onDestroy();
    binding = null;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    checkIfSignedIn(view);
    button.setOnClickListener(view1 -> {
      loginViewModel.signOut();
    });
  }

  private void checkIfSignedIn(View view)
  {
    loginViewModel.getCurrentUser()
        .observe(getViewLifecycleOwner(), firebaseUser -> {
          if (firebaseUser == null)
          {
            Navigation.findNavController(view)
                .navigate(LogoutFragmentDirections.actionLogoutNavToNavLogin());
          }
        });
  }
}
