package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import io.github.kutaiba_nezar_kashmar.newapp.R;
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
    getActivity().getSupportFragmentManager().beginTransaction()
        .replace(R.id.settings_container, new SettingFragmentPref()).commit();
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(root.getContext());
    String name = sharedPreferences.getString("Dark Theme", "");

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
    super.onViewCreated(view, savedInstanceState);
  }
}
