package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.ThemeUtils;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;

public class SettingFragment extends PreferenceFragmentCompat
{
  private SettingsViewModel settingsViewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState)
  {
    settingsViewModel = new ViewModelProvider(this)
        .get(SettingsViewModel.class);
    super.onCreate(savedInstanceState);
    settingsViewModel.setUpDarkMode()
        .observe(this, aBoolean -> {
          if (aBoolean)
          {
            AppCompatDelegate
                .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
          }
          else
          {
            AppCompatDelegate
                .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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
