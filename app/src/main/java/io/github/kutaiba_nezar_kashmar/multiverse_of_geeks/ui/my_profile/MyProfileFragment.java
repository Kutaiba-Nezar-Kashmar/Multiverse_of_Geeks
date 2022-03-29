package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.my_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.edit_profile.EditProfileViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login.LoginViewModel;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentCreateProfileBinding;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentLoginBinding;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentMyProfileBinding;

public class MyProfileFragment extends Fragment
{
  private FragmentMyProfileBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    MyProfileViewModel myProfileViewModel =
        new ViewModelProvider(this).get(MyProfileViewModel.class);

    binding = FragmentMyProfileBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


    return root;
  }

  @Override public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}
