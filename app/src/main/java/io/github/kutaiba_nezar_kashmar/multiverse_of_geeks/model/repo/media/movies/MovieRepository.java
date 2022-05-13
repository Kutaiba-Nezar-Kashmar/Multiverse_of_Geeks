package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;

public interface MovieRepository
{
  /**
   * Hold a list of SingleMovieResponse
   *
   * @return LiveData of al list of SingleMovieResponse object
   */
  LiveData<List<SingleMovieResponse>> getFavoriteMovies();

  /**
   * Add new SingleMovieResponse object to database
   *
   * @param movie The movie to be added
   */
  void insertFavoriteMovie(SingleMovieResponse movie);

  /**
   * Delete new SingleMovieResponse object to database
   *
   * @param movie The movie to be deleted
   */
  void deleteFavoriteMovie(SingleMovieResponse movie);

  /**
   * Hold a SingleMovieResponse object based on given parameter
   *
   * @param id The movie id
   * @return LiveData of a SingleMovieResponse object
   */
  LiveData<SingleMovieResponse> getSingleFavoriteMovie(int id);

  /**
   * Hold a SingleMovieResponse object based on given parameter
   *
   * @param id The movie id
   * @return MutableLiveData of a SingleMovieResponse object
   */
  MutableLiveData<SingleMovieResponse> findMovie(int id);

  /**
   * Hold a list of popular Movie object based on given parameter
   *
   * @param pageNumber The page number in the webserver that holds a list of movies
   * @return MutableLiveData of a list of  Movie object
   */
  MutableLiveData<List<Movie>> getAllPopularMovies(int pageNumber);

  /**
   * Hold a list of top rated Movie object based on given parameter
   *
   * @param pageNumber The page number in the webserver that holds a list of movies
   * @return MutableLiveData of a list of  Movie object
   */
  MutableLiveData<List<Movie>> getAllTopRatedMovies(int pageNumber);

  /**
   * Hold a list of playing now Movie object based on given parameter
   *
   * @param pageNumber The page number in the webserver that holds a list of movies
   * @return MutableLiveData of a list of Movie object
   */
  MutableLiveData<List<Movie>> getAllNowPlayingMovies(int pageNumber);

  /**
   * Hold a list of upcoming Movie object based on given parameter
   *
   * @param pageNumber The page number in the webserver that holds a list of movies
   * @return MutableLiveData of a list of  Movie object
   */
  MutableLiveData<List<Movie>> getAllUpcomingMovies(int pageNumber);

  /**
   * Hold a list of queried Movie object based on given parameter
   *
   * @param query The query used to search fo a movie or list of movies
   * @return MutableLiveData of a list of  Movie object
   */
  MutableLiveData<List<Movie>> getAllSearchedMoviesMovies(String query);

  /**
   * Hold a list of similar Movie object based on given parameter
   *
   * @param id The movie id that have movies similar to it
   * @return MutableLiveData of a list of  Movie object
   */
  MutableLiveData<List<Movie>> getAllSimilarMovies(int id);
}
