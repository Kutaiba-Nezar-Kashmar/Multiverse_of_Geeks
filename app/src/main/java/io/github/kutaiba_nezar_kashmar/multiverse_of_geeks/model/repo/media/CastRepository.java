package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Cast;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;

public interface CastRepository
{
  MutableLiveData<ArrayList<Cast>> getMovieCast(int movieId);
  MutableLiveData<ArrayList<Cast>> getTvShowCast(int tvShowId);
}
