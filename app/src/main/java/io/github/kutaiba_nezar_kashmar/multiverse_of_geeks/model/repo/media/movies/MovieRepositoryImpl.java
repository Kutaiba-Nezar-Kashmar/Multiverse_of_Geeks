package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.movies;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.movies.MoviesDAO;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.GeekDatabase;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.CommentResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.MovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.media.movie_network.MovieAPI;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepositoryImpl implements MovieRepository
{
  private static MovieRepository instance;
  private final LiveData<List<SingleMovieResponse>> favoritMovies;
  private final LiveData<SingleMovieResponse> singleFavoriteMovie;
  private final MutableLiveData<SingleMovieResponse> singleMovie;
  private final MutableLiveData<List<Movie>> popularMovies;
  private final MutableLiveData<List<Movie>> topRatedMovies;
  private final MutableLiveData<List<Movie>> nowPlayingMovies;
  private final MutableLiveData<List<Movie>> latestMovies;
  private final MutableLiveData<List<Movie>> upcomingMovies;
  private final MutableLiveData<List<Movie>> searchedMoviesMovies;
  private final MutableLiveData<List<Movie>> similarMovies;
  private final MutableLiveData<List<Comment>> movieReviews;
  private final ExecutorService executorService;
  private final MoviesDAO moviesDAO;

  private MovieRepositoryImpl(Application application)
  {
    GeekDatabase database = GeekDatabase.getInstance(application);
    moviesDAO = database.moviesDAO();
    executorService = Executors.newFixedThreadPool(2);
    favoritMovies = moviesDAO.getAllFavoriteMovies();
    singleFavoriteMovie = new MutableLiveData<>();
    singleMovie = new MutableLiveData<>();
    popularMovies = new MutableLiveData<>();
    topRatedMovies = new MutableLiveData<>();
    nowPlayingMovies = new MutableLiveData<>();
    latestMovies = new MutableLiveData<>();
    upcomingMovies = new MutableLiveData<>();
    searchedMoviesMovies = new MutableLiveData<>();
    similarMovies = new MutableLiveData<>();
    movieReviews = new MutableLiveData<>();
  }

  public static synchronized MovieRepository getInstance(
      Application application)
  {
    if (instance == null)
    {
      instance = new MovieRepositoryImpl(application);
    }
    return instance;
  }

  @Override
  public LiveData<List<SingleMovieResponse>> getFavoriteMovies()
  {
    return favoritMovies;
  }

  @Override
  public void insertFavoriteMovie(SingleMovieResponse movie)
  {
    executorService.execute(() -> moviesDAO.insertMovie(movie));
  }

  @Override
  public void deleteFavoriteMovie(SingleMovieResponse movie)
  {
    executorService.execute(() -> moviesDAO.deleteMovie(movie));
  }

  @Override
  public LiveData<SingleMovieResponse> getSingleFavoriteMovie(int id)
  {
    return moviesDAO.getMovieById(id);
  }

  @Override
  public MutableLiveData<SingleMovieResponse> findMovie(int id)
  {
    MovieAPI movieAPI = MediaClient.getMovieAPI();
    Call<SingleMovieResponse> call = movieAPI
        .getMovieById(id, BuildConfig.API_KEY);
    call.enqueue(new Callback<SingleMovieResponse>()
    {
      @Override
      public void onResponse(Call<SingleMovieResponse> call,
          Response<SingleMovieResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            singleMovie.setValue(response.body());
          }
        }
      }

      @Override
      public void onFailure(Call<SingleMovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return singleMovie;
  }

  @Override
  public MutableLiveData<List<Movie>> getAllPopularMovies(int pageNumber)
  {
    MovieAPI movieAPI = MediaClient.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .getAllPopularMovies(BuildConfig.API_KEY, pageNumber);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            popularMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return popularMovies;
  }

  @Override
  public MutableLiveData<List<Movie>> getAllTopRatedMovies(int pageNumber)
  {
    MovieAPI movieAPI = MediaClient.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .getAllTopRatedMovies(BuildConfig.API_KEY, pageNumber);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            topRatedMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return topRatedMovies;
  }

  @Override
  public MutableLiveData<List<Movie>> getAllNowPlayingMovies(
      int pageNumber)
  {
    MovieAPI movieAPI = MediaClient.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .getAllNowPlayingMovies(BuildConfig.API_KEY, pageNumber);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            nowPlayingMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return nowPlayingMovies;
  }

  @Override
  public MutableLiveData<List<Movie>> getAllLatestMovies(int pageNumber)
  {
    MovieAPI movieAPI = MediaClient.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .getAllLatestMovies(BuildConfig.API_KEY, pageNumber);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            latestMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return latestMovies;
  }

  @Override
  public MutableLiveData<List<Movie>> getAllUpcomingMovies(int pageNumber)
  {
    MovieAPI movieAPI = MediaClient.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .getAllUpComingsMovies(BuildConfig.API_KEY, pageNumber);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            upcomingMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return upcomingMovies;
  }

  @Override
  public MutableLiveData<List<Movie>> getAllSearchedMoviesMovies(
      String query)
  {
    MovieAPI movieAPI = MediaClient.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .searchForMovie(BuildConfig.API_KEY, query);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            searchedMoviesMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return searchedMoviesMovies;
  }

  @Override
  public MutableLiveData<List<Movie>> getAllSimilarMovies(int id)
  {
    MovieAPI movieAPI = MediaClient.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .getSimilarMovies(id, BuildConfig.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            similarMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return similarMovies;
  }

  @Override
  public MutableLiveData<List<Comment>> getMovieReviews(int id)
  {
    MovieAPI movieAPI = MediaClient.getMovieAPI();
    Call<CommentResponse> call = movieAPI
        .getMovieReviews(id, BuildConfig.API_KEY);
    call.enqueue(new Callback<CommentResponse>()
    {
      @Override
      public void onResponse(Call<CommentResponse> call,
          Response<CommentResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            movieReviews.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<CommentResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return movieReviews;
  }

}
