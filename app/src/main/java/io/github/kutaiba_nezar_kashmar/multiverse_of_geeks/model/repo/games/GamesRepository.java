package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Game;

public interface GamesRepository
{
  /**
   * Hold a list of Game in A liveData
   *
   * @return MutableLiveData of al list of Game object
   */
  MutableLiveData<List<Game>> getAllGames();

  /**
   * Hold a list of Game in A liveData based on given parameter
   *
   * @param query The string which is sent to query for a game
   * @return MutableLiveData of al list of Game object
   */
  MutableLiveData<List<Game>> getSearchedGames(String query);

  /**
   * Hold a list of Game in A liveData based on given parameter
   *
   * @param id The game id
   * @return MutableLiveData of al list of Game object
   */
  MutableLiveData<Game> getGameById(int id);

  /**
   * Hold a list of Game in A liveData
   *
   * @return LiveData of al list of Game object
   */
  LiveData<List<Game>> getFavoriteGames();

  /**
   * Hold a list of Game in A liveData based on given parameter
   *
   * @param id The game id
   * @return LiveData of al list of Game object
   */
  LiveData<Game> getSingleFavoriteGame(int id);

  /**
   * Add a Game object to database
   *
   * @param game The game to be added
   */
  void insertFavoriteGame(Game game);

  /**
   * Delete a Game object to database
   *
   * @param game The game to be deleted
   */
  void deleteFavoriteGame(Game game);
}
