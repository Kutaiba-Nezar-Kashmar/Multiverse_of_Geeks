package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;
import io.reactivex.rxjava3.core.Flowable;

public interface MovieRepository
{
  LiveData<List<SingleMovieResponse>> getFavoriteMovies();
  void insertFavoriteMovie(SingleMovieResponse movie);
  void deleteFavoriteMovie(SingleMovieResponse movie);
  LiveData<SingleMovieResponse> getSingleFavoriteMovie(int id);
  MutableLiveData<SingleMovieResponse> findMovie(int id);
  MutableLiveData<List<Movie>> getAllPopularMovies(int pageNumber);
  MutableLiveData<List<Movie>> getAllTopRatedMovies(int pageNumber);
  MutableLiveData<List<Movie>> getAllNowPlayingMovies(int pageNumber);
  MutableLiveData<List<Movie>> getAllLatestMovies(int pageNumber);
  MutableLiveData<List<Movie>> getAllUpcomingMovies(int pageNumber);
  MutableLiveData<List<Movie>> getAllSearchedMoviesMovies(String query);
  MutableLiveData<List<Movie>> getAllSimilarMovies(int id);
  MutableLiveData<List<Comment>> getMovieReviews(int id);
}
