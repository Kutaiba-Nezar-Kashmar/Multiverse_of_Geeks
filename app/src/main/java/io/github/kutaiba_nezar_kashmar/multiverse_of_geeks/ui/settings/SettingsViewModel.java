package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.settings;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

public class SettingsViewModel extends AndroidViewModel
{
  private final MutableLiveData<Boolean> isDarkTheme;

  public SettingsViewModel(@NonNull Application application)
  {
    super(application);
    isDarkTheme = new MutableLiveData<>();
  }

  public MutableLiveData<Boolean> setUpDarkMode()
  {
    SharedPreferences sharedPreferences = PreferenceManager
        .getDefaultSharedPreferences(getApplication());
    boolean isDark = sharedPreferences.getBoolean("dark_theme", false);

    sharedPreferences
        .registerOnSharedPreferenceChangeListener((sharedPrefs, s) -> {
          if (s.equals("dark_theme"))
          {
            boolean setIsDark = sharedPrefs.getBoolean("dark_theme", false);
            isDarkTheme.setValue(setIsDark);
          }
        });
    isDarkTheme.setValue(isDark);
    return isDarkTheme;
  }
}
