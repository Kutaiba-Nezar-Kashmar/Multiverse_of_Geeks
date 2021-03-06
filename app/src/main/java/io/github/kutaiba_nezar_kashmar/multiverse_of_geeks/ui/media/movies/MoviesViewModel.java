package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.Objects;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieComment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.movie.MovieCommentRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.movie.MovieCommentRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.movies.MovieRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.movies.MovieRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.movie.MovieReviewRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.movie.MovieReviewRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserStorageRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserStorageRepositoryImpl;

public class MoviesViewModel extends AndroidViewModel
{
  private final MovieRepository movieRepository;
  private final UserRepository userRepository;
  private final MovieReviewRepository movieReviewRepository;
  private final MovieCommentRepository movieCommentRepository;
  private final UserStorageRepository userStorageRepository;

  @RequiresApi(api = Build.VERSION_CODES.O)
  public MoviesViewModel(Application application)
  {
    super(application);
    movieRepository = MovieRepositoryImpl.getInstance(application);
    userRepository = UserRepositoryImpl.getInstance(application);
    movieReviewRepository = MovieReviewRepositoryImpl.getInstance();
    movieCommentRepository = MovieCommentRepositoryImpl.getInstance();
    userStorageRepository = UserStorageRepositoryImpl.getInstance();
  }

  public LiveData<FirebaseUser> getCurrentUser()
  {
    return userRepository.getCurrentUser();
  }

  public StorageReference getProfileImage(String userId)
  {
    return userStorageRepository.getUserProfileImage(userId);
  }

  public void postReview(MovieReview movieReview)
  {
    String userId = Objects.requireNonNull(
        userRepository.getCurrentUser().getValue()).getUid();
    movieReviewRepository.postReview(movieReview, userId);
  }

  public LiveData<List<MovieReview>> getMovieReviews()
  {
    return movieReviewRepository.getMovieReviews();
  }

  public float calculateAverage(List<MovieReview> movieReviews)
  {
    //Get all reviews for a Movie and find average for them
    float total = 0;
    for (MovieReview mr : movieReviews)
    {
      total += mr.getRating();
    }
    return total / movieReviews.size();
  }

  public void postComment(MovieComment comment)
  {
    String userId = Objects.requireNonNull(
        userRepository.getCurrentUser().getValue()).getUid();
    movieCommentRepository.postComment(comment, userId);
  }

  public LiveData<List<MovieComment>> getMovieComments(int movieId)
  {
    return movieCommentRepository.movieComments(movieId);
  }

  public LiveData<List<SingleMovieResponse>> getFavoriteMovies()
  {
    return movieRepository.getFavoriteMovies();
  }

  public LiveData<SingleMovieResponse> getSingleFavoriteMovie(int id)
  {
    return movieRepository.getSingleFavoriteMovie(id);
  }

  public void insertMovie(SingleMovieResponse movie)
  {
    movieRepository.insertFavoriteMovie(movie);
  }

  public void deleteMovie(SingleMovieResponse movie)
  {
    movieRepository.deleteFavoriteMovie(movie);
  }

  public LiveData<SingleMovieResponse> findMovieById(int id)
  {
    return movieRepository.findMovie(id);
  }

  public LiveData<List<Movie>> getAllPopularMovies(int pageNumber)
  {
    return movieRepository.getAllPopularMovies(pageNumber);
  }

  public LiveData<List<Movie>> getAllTopRatedMovies(int pageNumber)
  {
    return movieRepository.getAllTopRatedMovies(pageNumber);
  }

  public LiveData<List<Movie>> getAllNowPlayingMovies(int pageNumber)
  {
    return movieRepository.getAllNowPlayingMovies(pageNumber);
  }

  public LiveData<List<Movie>> getAllUpcomingMovies(int pageNumber)
  {
    return movieRepository.getAllUpcomingMovies(pageNumber);
  }

  public LiveData<List<Movie>> getAllSearchedMoviesMovies(String arg)
  {
    return movieRepository.getAllSearchedMoviesMovies(arg);
  }

  public LiveData<List<Movie>> getAllSimilarMovies(int id)
  {
    return movieRepository.getAllSimilarMovies(id);
  }
}
