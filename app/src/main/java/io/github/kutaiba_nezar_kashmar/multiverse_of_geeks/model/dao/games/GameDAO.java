package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.games;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Game;

@Dao public interface GameDAO
{
  /**
   * Create new entry for game to Database
   *
   * @param game The game to be added
   */
  @Insert
  void insertGame(Game game);

  /**
   * Query list of games
   *
   * @return LiveData of a list of game object
   */
  @Query("SELECT * FROM game")
  LiveData<List<Game>> getAllFavoriteGames();

  /**
   * Query a single game by given parameter
   *
   * @param id The desired game Id
   * @return LiveData of a game object
   */
  @Query("SELECT * FROM game WHERE id = :id")
  LiveData<Game> getGameById(int id);

  /**
   * Delete a game object from database
   *
   * @param game The game to be deleted
   */
  @Delete
  void deleteGame(Game game);
}
