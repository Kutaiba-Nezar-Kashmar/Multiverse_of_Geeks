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
  MutableLiveData<ArrayList<Movie>> getAllPopularMovies(int pageNumber);
  MutableLiveData<ArrayList<Movie>> getAllTopRatedMovies(int pageNumber);
  MutableLiveData<ArrayList<Movie>> getAllNowPlayingMovies(int pageNumber);
  MutableLiveData<ArrayList<Movie>> getAllLatestMovies(int pageNumber);
  MutableLiveData<ArrayList<Movie>> getAllUpcomingMovies(int pageNumber);
  MutableLiveData<ArrayList<Movie>> getAllSearchedMoviesMovies(String query);
  MutableLiveData<ArrayList<Movie>> getAllSimilarMovies(int id);
  MutableLiveData<ArrayList<Comment>> getMovieReviews(int id);
}
