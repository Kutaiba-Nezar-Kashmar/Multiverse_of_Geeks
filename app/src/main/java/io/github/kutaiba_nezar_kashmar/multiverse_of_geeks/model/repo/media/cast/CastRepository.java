package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.cast;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Cast;

public interface CastRepository
{
  /**
   * Hold a list of Cast in A liveData based on given parameter
   *
   * @param movieId The movie id that the cast belong to
   * @return MutableLiveData of al list of Cast object
   */
  MutableLiveData<List<Cast>> getMovieCast(int movieId);

  /**
   * Hold a list of Cast in A liveData based on given parameter
   *
   * @param tvShowId The tv show  id that the cast belong to
   * @return MutableLiveData of al list of Cast object
   */
  MutableLiveData<List<Cast>> getTvShowCast(int tvShowId);
}
