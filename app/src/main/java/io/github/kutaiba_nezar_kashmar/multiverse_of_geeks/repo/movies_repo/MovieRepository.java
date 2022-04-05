package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.movies_repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.MovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.movie_network.MovieAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.movie_network.MovieServiceGenerator;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository
{
  private static MovieRepository instance;
  private final MutableLiveData<Movie> movie;
  private final MutableLiveData<ArrayList<Movie>> popularMovies;
  private final MutableLiveData<ArrayList<Movie>> latestMovies;

  private MovieRepository()
  {
    movie = new MutableLiveData<>();
    popularMovies = new MutableLiveData<>();
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

  public LiveData<Movie> getMovie()
  {
    return movie;
  }

  public LiveData<ArrayList<Movie>> getPopularMovies()
  {
    return popularMovies;
  }

  public LiveData<ArrayList<Movie>> getLatestMovies()
  {
    return latestMovies;
  }

  public MutableLiveData<Movie> findMovie(int id)
  {
    MovieAPI movieAPI = MovieServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI.getMovies(id, Constants.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          movie.setValue(response.body().getMovie());
        }
      }

      @Override public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return movie;
  }

  public MutableLiveData<ArrayList<Movie>> getAllPopularMovies()
  {
    MovieAPI movieAPI = MovieServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI.getAllPopularMovies(Constants.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          popularMovies.setValue(response.body().getResults());
        }
      }

      @Override public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return popularMovies;
  }

  public MutableLiveData<ArrayList<Movie>> getAllLatestMovies()
  {
    MovieAPI movieAPI = MovieServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI.getAllResentMovies(Constants.API_KEY);
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          latestMovies.setValue(response.body().getResults());
        }
      }

      @Override public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return latestMovies;
  }
}
