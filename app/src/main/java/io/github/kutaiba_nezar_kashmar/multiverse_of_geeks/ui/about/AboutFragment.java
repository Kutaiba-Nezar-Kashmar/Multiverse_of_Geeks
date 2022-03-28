package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.edit_profile.EditProfileViewModel;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentAboutBinding;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentCreateProfileBinding;

public class AboutFragment extends Fragment
{
  private FragmentAboutBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    EditProfileViewModel editProfileViewModel = new ViewModelProvider(this)
        .get(EditProfileViewModel.class);

    binding = FragmentAboutBinding.inflate(inflater, container, false);

    return binding.getRoot();
  }

  @Override public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}
