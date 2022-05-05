package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Game;

public interface GamesRepository
{
  MutableLiveData<List<Game>> getAllGames();
  MutableLiveData<List<Game>> getSearchedGames(String query);
  MutableLiveData<Game> getGameById(int id);
  LiveData<List<Game>> getFavoriteGames();
  LiveData<Game> getSingleFavoriteGame(int id);
  void insertFavoriteGame(Game game);
  void deleteFavoriteGame(Game game);
}
