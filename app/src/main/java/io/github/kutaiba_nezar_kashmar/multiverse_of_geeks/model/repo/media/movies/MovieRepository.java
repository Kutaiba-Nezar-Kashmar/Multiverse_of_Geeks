package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.movies;

import androidx.lifecycle.LiveData;

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
  Flowable<SingleMovieResponse> findMovie(int id);
  Flowable<ArrayList<Movie>> getAllPopularMovies(int pageNumber);
  Flowable<ArrayList<Movie>> getAllTopRatedMovies(int pageNumber);
  Flowable<ArrayList<Movie>> getAllNowPlayingMovies(int pageNumber);
  Flowable<ArrayList<Movie>> getAllLatestMovies(int pageNumber);
  Flowable<ArrayList<Movie>> getAllUpcomingMovies(int pageNumber);
  Flowable<ArrayList<Movie>> getAllSearchedMoviesMovies(String query);
  Flowable<ArrayList<Movie>> getAllSimilarMovies(int id);
  Flowable<ArrayList<Comment>> getMovieReviews(int id);
}
