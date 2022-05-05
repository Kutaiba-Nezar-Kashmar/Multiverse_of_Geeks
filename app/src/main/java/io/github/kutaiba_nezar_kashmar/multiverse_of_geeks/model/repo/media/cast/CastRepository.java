package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.cast;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Cast;

public interface CastRepository
{
  MutableLiveData<List<Cast>> getMovieCast(int movieId);
  MutableLiveData<List<Cast>> getTvShowCast(int tvShowId);
}
