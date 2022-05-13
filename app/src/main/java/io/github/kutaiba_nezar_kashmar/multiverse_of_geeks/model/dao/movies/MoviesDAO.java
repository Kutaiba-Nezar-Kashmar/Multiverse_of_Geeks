package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.movies;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;

@Dao public interface MoviesDAO
{
  /**
   * Create new entry for movie to Database
   *
   * @param movie The movie to be added
   */
  @Insert
  void insertMovie(SingleMovieResponse movie);

  /**
   * Query list of movies
   *
   * @return LiveData of a list of movie object
   */
  @Query("SELECT * FROM movie")
  LiveData<List<SingleMovieResponse>> getAllFavoriteMovies();

  /**
   * Query a single movie by given parameter
   *
   * @param id The desired movie Id
   * @return LiveData of a movie object
   */
  @Query("SELECT * FROM movie WHERE id = :id")
  LiveData<SingleMovieResponse> getMovieById(int id);

  /**
   * Delete a movie object from database
   *
   * @param movie The game to be deleted
   */
  @Delete
  void deleteMovie(SingleMovieResponse movie);
}
