package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.games.GameDAO;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.movies.MoviesDAO;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.tv.TVShowDAO;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;

@Database(entities = {SingleMovieResponse.class, SingleTvShowResponse.class,
    Game.class}, version = 3) public abstract class GeekDatabase
    extends RoomDatabase
{
  private static GeekDatabase instance;

  public abstract MoviesDAO moviesDAO();
  public abstract TVShowDAO tvShowDAO();
  public abstract GameDAO gameDAO();

  public static synchronized GeekDatabase getInstance(Context context)
  {
    if (instance == null)
    {
      instance = Room
          .databaseBuilder(context, GeekDatabase.class, "geek_database")
          .fallbackToDestructiveMigration().build();
    }
    return instance;
  }
}
