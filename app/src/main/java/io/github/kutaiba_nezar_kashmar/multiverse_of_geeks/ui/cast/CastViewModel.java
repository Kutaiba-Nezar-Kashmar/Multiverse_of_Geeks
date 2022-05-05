package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.cast;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Cast;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.cast.CastRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.cast.CastRepositoryImpl;

public class CastViewModel extends AndroidViewModel
{
  private final CastRepository castRepository;

  public CastViewModel(@NonNull Application application)
  {
    super(application);
    castRepository = CastRepositoryImpl.getInstance();
  }

  public LiveData<List<Cast>> getMovieCast(int movieId)
  {
    return castRepository.getMovieCast(movieId);
  }

  public LiveData<List<Cast>> getTvShowCast(int tvShowId)
  {
    return castRepository.getTvShowCast(tvShowId);
  }
}
