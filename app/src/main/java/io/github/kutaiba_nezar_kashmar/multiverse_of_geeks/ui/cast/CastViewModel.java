package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.cast;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Cast;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.CastRepository;

public class CastViewModel extends AndroidViewModel
{
  private CastRepository castRepository;

  public CastViewModel(@NonNull Application application)
  {
    super(application);
    castRepository = CastRepository.getInstance();
  }

  public LiveData<ArrayList<Cast>> getMovieCast(int movieId)
  {
    return castRepository.getMovieCast(movieId);
  }

  public LiveData<ArrayList<Cast>> getTvShowCast(int tvShowId)
  {
    return castRepository.getTvShowCast(tvShowId);
  }
}
