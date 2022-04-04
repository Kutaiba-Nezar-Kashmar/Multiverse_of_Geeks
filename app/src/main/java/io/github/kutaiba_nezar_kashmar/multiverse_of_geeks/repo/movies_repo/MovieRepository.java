package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.movies_repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.MovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.movie_network.MovieAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.movie_network.MovieServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository
{
  private static MovieRepository instance;
  private final MutableLiveData<Movie> movie;
  private final MutableLiveData<ArrayList<Movie>> movies;
  private ArrayList<Movie> movieArrayList = new ArrayList<>();

  private MovieRepository()
  {
    movie = new MutableLiveData<>();
    movies = new MutableLiveData<>();
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

  public LiveData<ArrayList<Movie>> getMovies()
  {
    return movies;
  }

  public MutableLiveData<Movie> findMovie(int id)
  {
    MovieAPI movieAPI = MovieServiceGenerator.getMovieAPI();
    Call<MovieResponse> call = movieAPI.getMovies(id);
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
    Call<MovieResponse> call = movieAPI.getAllPopularMovies();
    call.enqueue(new Callback<MovieResponse>()
    {
      @Override public void onResponse(Call<MovieResponse> call,
          Response<MovieResponse> response)
      {
        if (response.code() == 200)
        {
          movies.setValue(response.body().getResults());
        }
      }

      @Override public void onFailure(Call<MovieResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return movies;
  }
}
