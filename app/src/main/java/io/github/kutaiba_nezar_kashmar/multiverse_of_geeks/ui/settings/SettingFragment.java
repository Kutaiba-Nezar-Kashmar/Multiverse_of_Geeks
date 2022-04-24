package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentSettingsBinding;

public class SettingFragment extends Fragment
{
  private FragmentSettingsBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    binding = FragmentSettingsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    return root;
  }
}
