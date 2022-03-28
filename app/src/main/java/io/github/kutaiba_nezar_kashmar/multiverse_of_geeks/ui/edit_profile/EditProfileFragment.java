package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.edit_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends Fragment
{
  private FragmentEditProfileBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    EditProfileViewModel editProfileViewModel = new ViewModelProvider(this)
        .get(EditProfileViewModel.class);

    binding = FragmentEditProfileBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    return root;
  }

  @Override public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}
