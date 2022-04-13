package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.CommentResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.MovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.MediaServiceGenerator;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.media.movie_network.MovieAPI;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository
{
  private static MovieRepository instance;
  private final MutableLiveData<Movie> movie;
  private final MutableLiveData<ArrayList<Movie>> popularMovies;
  private final MutableLiveData<ArrayList<Movie>> topRatedMovies;
  private final MutableLiveData<ArrayList<Movie>> nowPlayingMovies;
  private final MutableLiveData<ArrayList<Movie>> upcomingMovies;
  private final MutableLiveData<ArrayList<Movie>> latestMovies;
  private final MutableLiveData<ArrayList<Movie>> searchedMovies;
  private final MutableLiveData<ArrayList<Movie>> similarMovies;
  private final MutableLiveData<ArrayList<Comment>> movieReviews;

  private MovieRepository()
  {
    movie = new MutableLiveData<>();
    popularMovies = new MutableLiveData<>();
    topRatedMovies = new MutableLiveData<>();
    nowPlayingMovies = new MutableLiveData<>();
    upcomingMovies = new MutableLiveData<>();
    searchedMovies = new MutableLiveData<>();
    movieReviews = new MutableLiveData<>();
    similarMovies = new MutableLiveData<>();
    latestMovies = new MutableLiveData<>();
  }

  public static synchronized MovieRepository getInstance()
  {
    if (instance == null)
    {
      instance = new MovieRepository();
    }
    return instance;
  }

  public MutableLiveData<Movie> findMovie(int id)
  {
    MovieAPI movieAPI = MediaServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI.getMovieById(id, BuildConfig.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<MovieResponse> call,
          @NonNull Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            movie.setValue(response.body().getMovie());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return movie;
  }

  public MutableLiveData<ArrayList<Movie>> getAllPopularMovies()
  {
    MovieAPI movieAPI = MediaServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI.getAllPopularMovies(BuildConfig.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<MovieResponse> call,
          @NonNull Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            popularMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return popularMovies;
  }

  public MutableLiveData<ArrayList<Movie>> getAllTopRatedMovies()
  {
    MovieAPI movieAPI = MediaServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI.getAllTopRatedMovies(BuildConfig.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<MovieResponse> call,
          @NonNull Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            topRatedMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return topRatedMovies;
  }

  public MutableLiveData<ArrayList<Movie>> getAllNowPlayingMovies()
  {
    MovieAPI movieAPI = MediaServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .getAllNowPlayingMovies(BuildConfig.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<MovieResponse> call,
          @NonNull Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            nowPlayingMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return nowPlayingMovies;
  }

  public MutableLiveData<ArrayList<Movie>> getAllLatestMovies()
  {
    MovieAPI movieAPI = MediaServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .getAllLatestMovies(BuildConfig.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<MovieResponse> call,
          @NonNull Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            latestMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return latestMovies;
  }

  public MutableLiveData<ArrayList<Movie>> getAllUpcomingMovies()
  {
    MovieAPI movieAPI = MediaServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .getAllUpComingsMovies(BuildConfig.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<MovieResponse> call,
          @NonNull Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            upcomingMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return upcomingMovies;
  }

  public MutableLiveData<ArrayList<Movie>> getAllSearchedMoviesMovies(
      String query)
  {
    MovieAPI movieAPI = MediaServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .searchForMovie(BuildConfig.API_KEY, query);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<MovieResponse> call,
          @NonNull Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            searchedMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return searchedMovies;
  }

  public MutableLiveData<ArrayList<Movie>> getAllSimilarMovies(int id)
  {
    MovieAPI movieAPI = MediaServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI.getSimilarMovies(id, BuildConfig.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<MovieResponse> call,
          @NonNull Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            similarMovies.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<MovieResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return similarMovies;
  }

  public MutableLiveData<ArrayList<Comment>> getMovieReviews(int id)
  {
    MovieAPI movieAPI = MediaServiceGenerator.getMovieAPI();
    Call<CommentResponse> call = movieAPI
        .getMovieReviews(id, BuildConfig.API_KEY);
    call.enqueue(new Callback<CommentResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<CommentResponse> call,
          @NonNull Response<CommentResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            movieReviews.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<CommentResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return movieReviews;
  }
}
