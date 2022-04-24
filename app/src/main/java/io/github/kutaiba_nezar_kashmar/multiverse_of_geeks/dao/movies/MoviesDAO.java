package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.dao.movies;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.movie_responses.SingleMovieResponse;

@Dao
public interface MoviesDAO
{
  @Insert
  void insertMovie(SingleMovieResponse movie);

  @Query("SELECT * FROM movie")
  LiveData<List<SingleMovieResponse>> getAllFavoriteMovies();

  @Query("SELECT * FROM movie WHERE id = :id")
  LiveData<SingleMovieResponse> getMovieById(int id);
}
