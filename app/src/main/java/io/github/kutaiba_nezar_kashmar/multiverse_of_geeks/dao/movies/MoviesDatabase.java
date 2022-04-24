package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.dao.movies;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.movie_responses.SingleMovieResponse;

@Database(entities = {SingleMovieResponse.class},
    version = 1) public abstract class MoviesDatabase extends RoomDatabase
{
  private static MoviesDatabase instance;

  public abstract MoviesDAO moviesDAO();

  public static synchronized MoviesDatabase getInstance(Context context)
  {
    if (instance == null)
    {
      instance = Room
          .databaseBuilder(context, MoviesDatabase.class, "movies_database")
          .fallbackToDestructiveMigration().build();
    }
    return instance;
  }
}
