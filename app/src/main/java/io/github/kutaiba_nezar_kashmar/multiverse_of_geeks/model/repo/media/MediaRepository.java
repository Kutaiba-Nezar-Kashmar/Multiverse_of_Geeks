package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Trending;
import io.reactivex.rxjava3.core.Flowable;

public interface MediaRepository
{
  Flowable<ArrayList<Trending>> getTrendingToday();
}
