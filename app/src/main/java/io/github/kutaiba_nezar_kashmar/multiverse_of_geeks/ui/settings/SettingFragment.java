package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.settings;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;

public class SettingFragment extends PreferenceFragmentCompat
{

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState)
  {
    SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(
        SettingsViewModel.class);
    super.onCreate(savedInstanceState);
    settingsViewModel.setUpDarkMode().observe(this, aBoolean -> {
      if (aBoolean)
      {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
      }
      else
      {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
      }
    });
  }

  @Override
  public void onCreatePreferences(@Nullable Bundle savedInstanceState,
      @Nullable String rootKey)
  {
    setPreferencesFromResource(R.xml.preferences, rootKey);
  }
}
