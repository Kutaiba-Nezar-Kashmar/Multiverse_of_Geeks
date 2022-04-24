package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.settings;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import io.github.kutaiba_nezar_kashmar.newapp.R;

public class SettingFragmentPref extends PreferenceFragmentCompat
{
  @Override
  public void onCreatePreferences(@Nullable Bundle savedInstanceState,
      @Nullable String rootKey)
  {
    setPreferencesFromResource(R.xml.preferences, rootKey);
  }
}
