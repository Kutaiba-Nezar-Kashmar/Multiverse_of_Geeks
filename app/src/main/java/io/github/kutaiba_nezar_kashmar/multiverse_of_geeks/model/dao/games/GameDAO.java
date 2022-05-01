package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.games;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Game;

@Dao public interface GameDAO
{
  @Insert
  void insertGame(Game game);

  @Query("SELECT * FROM game")
  LiveData<List<Game>> getAllFavoriteGames();

  @Query("SELECT * FROM game WHERE id = :id")
  LiveData<Game> getGameById(int id);

  @Delete
  void deleteGame(Game game);
}
