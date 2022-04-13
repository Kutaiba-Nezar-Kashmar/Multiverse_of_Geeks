package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.CommentResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.TvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.MediaServiceGenerator;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.media.Tv_show_network.TVShowAPI;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
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
  private final MutableLiveData<TvShow> tvShow;
  private final MutableLiveData<ArrayList<Comment>> tvReviews;

  private TVShowRepository()
  {
    popularTvShows = new MutableLiveData<>();
    topRatedTvShows = new MutableLiveData<>();
    onAirTvShows = new MutableLiveData<>();
    airingTodayTvShows = new MutableLiveData<>();
    searchedTvShows = new MutableLiveData<>();
    tvShow = new MutableLiveData<>();
    tvReviews = new MutableLiveData<>();
  }

  public static synchronized TVShowRepository getInstance()
  {
    if (instance == null)
    {
      instance = new TVShowRepository();
    }
    return instance;
  }

  public MutableLiveData<TvShow> findTvShowById(int id)
  {
    TVShowAPI tvShowAPI = MediaServiceGenerator.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI.getTvShowById(id, BuildConfig.API_KEY);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<TvShowResponse> call,
          @NonNull Response<TvShowResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            tvShow.setValue(response.body().getTvShow());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<TvShowResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return tvShow;
  }

  public MutableLiveData<ArrayList<TvShow>> getAllPopularTvShows()
  {
    TVShowAPI tvShowAPI = MediaServiceGenerator.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI
        .getAllPopularTvShows(BuildConfig.API_KEY);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<TvShowResponse> call,
          @NonNull Response<TvShowResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            popularTvShows.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<TvShowResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return popularTvShows;
  }

  public MutableLiveData<ArrayList<TvShow>> getAllTopRatedTvShows()
  {
    TVShowAPI tvShowAPI = MediaServiceGenerator.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI
        .getAllTopRatedTvShows(BuildConfig.API_KEY);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<TvShowResponse> call,
          @NonNull Response<TvShowResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            topRatedTvShows.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<TvShowResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return topRatedTvShows;
  }

  public MutableLiveData<ArrayList<TvShow>> getAllOnAirTvShows()
  {
    TVShowAPI tvShowAPI = MediaServiceGenerator.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI.getAllOnAirTvShows(BuildConfig.API_KEY);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<TvShowResponse> call,
          @NonNull Response<TvShowResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            onAirTvShows.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<TvShowResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return onAirTvShows;
  }

  public MutableLiveData<ArrayList<TvShow>> getAllAiringTodayTvShows()
  {
    TVShowAPI tvShowAPI = MediaServiceGenerator.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI
        .getAllAiringTodayTvShows(BuildConfig.API_KEY);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<TvShowResponse> call,
          @NonNull Response<TvShowResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            airingTodayTvShows.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<TvShowResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return airingTodayTvShows;
  }

  public MutableLiveData<ArrayList<TvShow>> getAllSearchedTvShows(String arg)
  {
    TVShowAPI tvShowAPI = MediaServiceGenerator.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI
        .searchForTvShow(BuildConfig.API_KEY, arg);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(@NonNull Call<TvShowResponse> call,
          @NonNull Response<TvShowResponse> response)
      {
        if (response.code() == 200)
        {
          if (response.body() != null)
          {
            searchedTvShows.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(@NonNull Call<TvShowResponse> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return searchedTvShows;
  }

  public MutableLiveData<ArrayList<Comment>> getTvReviews(int id)
  {
    TVShowAPI tvShowAPI = MediaServiceGenerator.getTVShowAPI();
    Call<CommentResponse> call = tvShowAPI.getTvReviews(id, BuildConfig.API_KEY);
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
            tvReviews.setValue(response.body().getResults());
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
    return tvReviews;
  }
}
