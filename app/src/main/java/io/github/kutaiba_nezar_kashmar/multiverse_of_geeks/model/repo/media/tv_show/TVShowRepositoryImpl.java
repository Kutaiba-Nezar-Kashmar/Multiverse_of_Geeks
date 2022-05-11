package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.tv_show;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.BuildConfig;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.GeekDatabase;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.tv.TVShowDAO;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.CommentResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.TvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.media.Tv_show_network.TVShowAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowRepositoryImpl implements TvShowRepository
{
  private static TvShowRepository instance;
  private final ExecutorService executorService;
  private final TVShowDAO tvShowDAO;
  private final LiveData<List<SingleTvShowResponse>> favoriteTvShows;
  private final LiveData<SingleTvShowResponse> singleFavoriteTvShow;
  private final MutableLiveData<SingleTvShowResponse> singleTvShow;
  private final MutableLiveData<List<TvShow>> popularTvShows;
  private final MutableLiveData<List<TvShow>> topRatedTvShows;
  private final MutableLiveData<List<TvShow>> onAirTvShows;
  private final MutableLiveData<List<TvShow>> airingTodayTvShows;
  private final MutableLiveData<List<TvShow>> searchedTvShows;
  private final MutableLiveData<List<Comment>> tvReviews;

  private TVShowRepositoryImpl(Application application)
  {
    GeekDatabase database = GeekDatabase.getInstance(application);
    tvShowDAO = database.tvShowDAO();
    executorService = Executors.newFixedThreadPool(2);
    favoriteTvShows = tvShowDAO.getAllFavoriteTvShows();
    singleFavoriteTvShow = new MutableLiveData<>();
    singleTvShow = new MutableLiveData<>();
    popularTvShows = new MutableLiveData<>();
    topRatedTvShows = new MutableLiveData<>();
    onAirTvShows = new MutableLiveData<>();
    airingTodayTvShows = new MutableLiveData<>();
    searchedTvShows = new MutableLiveData<>();
    tvReviews = new MutableLiveData<>();
  }

  public static synchronized TvShowRepository getInstance(
      Application application)
  {
    if (instance == null)
    {
      instance = new TVShowRepositoryImpl(application);
    }
    return instance;
  }

  @Override
  public LiveData<List<SingleTvShowResponse>> getFavoriteTvShows()
  {
    return favoriteTvShows;
  }

  @Override
  public void insertFavoriteTvShow(SingleTvShowResponse tv)
  {
    executorService.execute(() -> tvShowDAO.insertTvShow(tv));
  }

  @Override
  public void deleteFavoriteTvShow(SingleTvShowResponse tv)
  {
    executorService.execute(() -> tvShowDAO.deleteTvShow(tv));
  }

  @Override
  public LiveData<SingleTvShowResponse> getSingleFavoriteTvShow(int id)
  {
    return tvShowDAO.getTvShowById(id);
  }

  @Override
  public MutableLiveData<SingleTvShowResponse> findTvShowById(int id)
  {
    TVShowAPI tvShowAPI = MediaClient.getTVShowAPI();
    Call<SingleTvShowResponse> call = tvShowAPI
        .getTvShowById(id, BuildConfig.API_KEY);
    call.enqueue(new Callback<SingleTvShowResponse>()
    {
      @Override
      public void onResponse(Call<SingleTvShowResponse> call,
          Response<SingleTvShowResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            singleTvShow.setValue(response.body());
          }
        }
      }

      @Override
      public void onFailure(Call<SingleTvShowResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return singleTvShow;
  }

  @Override
  public MutableLiveData<List<TvShow>> getAllPopularTvShows(int pageNumber)
  {
    TVShowAPI tvShowAPI = MediaClient.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI
        .getAllPopularTvShows(BuildConfig.API_KEY, pageNumber);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(Call<TvShowResponse> call,
          Response<TvShowResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            popularTvShows.setValue(response.body().getResults());
          }
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

  @Override
  public MutableLiveData<List<TvShow>> getAllTopRatedTvShows(int pageNumber)
  {
    TVShowAPI tvShowAPI = MediaClient.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI
        .getAllTopRatedTvShows(BuildConfig.API_KEY, pageNumber);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(Call<TvShowResponse> call,
          Response<TvShowResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            topRatedTvShows.setValue(response.body().getResults());
          }
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

  @Override
  public MutableLiveData<List<TvShow>> getAllOnAirTvShows(int pageNumber)
  {
    TVShowAPI tvShowAPI = MediaClient.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI
        .getAllOnAirTvShows(BuildConfig.API_KEY, pageNumber);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(Call<TvShowResponse> call,
          Response<TvShowResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            onAirTvShows.setValue(response.body().getResults());
          }
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

  @Override
  public MutableLiveData<List<TvShow>> getAllAiringTodayTvShows(int pageNumber)
  {
    TVShowAPI tvShowAPI = MediaClient.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI
        .getAllAiringTodayTvShows(BuildConfig.API_KEY, pageNumber);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(Call<TvShowResponse> call,
          Response<TvShowResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            airingTodayTvShows.setValue(response.body().getResults());
          }
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

  @Override
  public MutableLiveData<List<TvShow>> getAllSearchedTvShows(String arg)
  {
    TVShowAPI tvShowAPI = MediaClient.getTVShowAPI();
    Call<TvShowResponse> call = tvShowAPI
        .searchForTvShow(BuildConfig.API_KEY, arg);
    call.enqueue(new Callback<TvShowResponse>()
    {
      @Override
      public void onResponse(Call<TvShowResponse> call,
          Response<TvShowResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            searchedTvShows.setValue(response.body().getResults());
          }
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

  @Override
  public MutableLiveData<List<Comment>> getTvReviews(int id)
  {
    TVShowAPI tvShowAPI = MediaClient.getTVShowAPI();
    Call<CommentResponse> call = tvShowAPI
        .getTvReviews(id, BuildConfig.API_KEY);
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
            tvReviews.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<CommentResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return tvReviews;
  }
}
