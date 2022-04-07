package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.TvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.MovieTVServiceGenerator;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.Tv_show_network.TVShowAPI;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowRepository
{
  private static TVShowRepository instance;
  private final MutableLiveData<ArrayList<TvShow>> popularTvShows;
  private final MutableLiveData<ArrayList<TvShow>> topRatedTvShows;
  private final MutableLiveData<ArrayList<TvShow>> onAirTvShows;
  private final MutableLiveData<ArrayList<TvShow>> airingTodayTvShows;
  private final MutableLiveData<ArrayList<TvShow>> searchedTvShows;

  private TVShowRepository()
  {
    popularTvShows = new MutableLiveData<>();
    topRatedTvShows = new MutableLiveData<>();
    onAirTvShows = new MutableLiveData<>();
    airingTodayTvShows = new MutableLiveData<>();
    searchedTvShows = new MutableLiveData<>();
  }

  public static synchronized TVShowRepository getInstance()
  {
    if (instance == null)
    {
      instance = new TVShowRepository();
    }
    return instance;
  }

  public MutableLiveData<ArrayList<TvShow>> getAllPopularTvShows()
  {
    TVShowAPI tvShowAPI = MovieTVServiceGenerator.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI.getAllPopularTvShows(Constants.API_KEY);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(Call<TvShowResponse> call,
          Response<TvShowResponse> response)
      {
        if (response.code() == 200)
        {
          popularTvShows.setValue(response.body().getResults());
        }
      }

      @Override
      public void onFailure(Call<TvShowResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return popularTvShows;
  }

  public MutableLiveData<ArrayList<TvShow>> getAllTopRatedTvShows()
  {
    TVShowAPI tvShowAPI = MovieTVServiceGenerator.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI.getAllTopRatedTvShows(Constants.API_KEY);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(Call<TvShowResponse> call,
          Response<TvShowResponse> response)
      {
        if (response.code() == 200)
        {
          topRatedTvShows.setValue(response.body().getResults());
        }
      }

      @Override
      public void onFailure(Call<TvShowResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return topRatedTvShows;
  }

  public MutableLiveData<ArrayList<TvShow>> getAllOnAirTvShows()
  {
    TVShowAPI tvShowAPI = MovieTVServiceGenerator.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI.getAllOnAirTvShows(Constants.API_KEY);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(Call<TvShowResponse> call,
          Response<TvShowResponse> response)
      {
        if (response.code() == 200)
        {
          onAirTvShows.setValue(response.body().getResults());
        }
      }

      @Override
      public void onFailure(Call<TvShowResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return onAirTvShows;
  }

  public MutableLiveData<ArrayList<TvShow>> getAllAiringTodayTvShows()
  {
    TVShowAPI tvShowAPI = MovieTVServiceGenerator.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI.getAllAiringTodayTvShows(Constants.API_KEY);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(Call<TvShowResponse> call,
          Response<TvShowResponse> response)
      {
        if (response.code() == 200)
        {
          airingTodayTvShows.setValue(response.body().getResults());
        }
      }

      @Override
      public void onFailure(Call<TvShowResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return airingTodayTvShows;
  }

  public MutableLiveData<ArrayList<TvShow>> getAllSearchedTvShows(String arg)
  {
    TVShowAPI tvShowAPI = MovieTVServiceGenerator.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI.searchForTvShow(Constants.API_KEY, arg);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(Call<TvShowResponse> call,
          Response<TvShowResponse> response)
      {
        if (response.code() == 200)
        {
          searchedTvShows.setValue(response.body().getResults());
        }
      }

      @Override
      public void onFailure(Call<TvShowResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return searchedTvShows;
  }
}
