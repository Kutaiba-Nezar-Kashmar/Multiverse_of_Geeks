package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel
{

  private final MutableLiveData<String> mText;

  public SlideshowViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is slideshow fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}