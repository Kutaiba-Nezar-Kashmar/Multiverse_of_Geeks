package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Cast;
import io.reactivex.rxjava3.core.Flowable;

public interface CastRepository
{
  Flowable<ArrayList<Cast>> getMovieCast(int movieId);
  public Flowable<ArrayList<Cast>> getTvShowCast(int tvShowId);
}
