package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.MovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.movie_network.MovieAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.MovieTVServiceGenerator;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.Constants;
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
  private final MutableLiveData<ArrayList<Movie>> searchedMovies;

  private MovieRepository()
  {
    movie = new MutableLiveData<>();
    popularMovies = new MutableLiveData<>();
    topRatedMovies = new MutableLiveData<>();
    nowPlayingMovies = new MutableLiveData<>();
    upcomingMovies = new MutableLiveData<>();
    searchedMovies = new MutableLiveData<>();
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
    MovieAPI movieAPI = MovieTVServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI.getMovieById(id, Constants.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          movie.setValue(response.body().getMovie());
        }
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return movie;
  }

  public MutableLiveData<ArrayList<Movie>> getAllPopularMovies()
  {
    MovieAPI movieAPI = MovieTVServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI.getAllPopularMovies(Constants.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          popularMovies.setValue(response.body().getResults());
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

  public MutableLiveData<ArrayList<Movie>> getAllTopRatedMovies()
  {
    MovieAPI movieAPI = MovieTVServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI.getAllTopRatedMovies(Constants.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          topRatedMovies.setValue(response.body().getResults());
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

  public MutableLiveData<ArrayList<Movie>> getAllNowPlayingMovies()
  {
    MovieAPI movieAPI = MovieTVServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .getAllNowPlayingMovies(Constants.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          nowPlayingMovies.setValue(response.body().getResults());
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

  public MutableLiveData<ArrayList<Movie>> getAllUpcomingMovies()
  {
    MovieAPI movieAPI = MovieTVServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .getAllUpComingsMovies(Constants.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          upcomingMovies.setValue(response.body().getResults());
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

  public MutableLiveData<ArrayList<Movie>> getAllSearchedMoviesMovies(
      String query)
  {
    MovieAPI movieAPI = MovieTVServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI
        .searchForMovie(Constants.API_KEY, query);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override
      public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          searchedMovies.setValue(response.body().getResults());
        }
      }

      @Override
      public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return searchedMovies;
  }
}
