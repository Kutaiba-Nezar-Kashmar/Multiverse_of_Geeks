package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel
{

  private final MutableLiveData<String> mText;

  public HomeViewModel()
  {
    mText = new MutableLiveData<>();
    mText.setValue("This is home fragment");
  }

  public LiveData<String> getText()
  {
    return mText;
  }
}