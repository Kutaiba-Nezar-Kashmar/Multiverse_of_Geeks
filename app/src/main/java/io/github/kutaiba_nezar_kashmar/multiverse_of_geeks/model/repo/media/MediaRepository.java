package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Trending;

public interface MediaRepository
{
  MutableLiveData<List<Trending>> getTrendingToday();
}
