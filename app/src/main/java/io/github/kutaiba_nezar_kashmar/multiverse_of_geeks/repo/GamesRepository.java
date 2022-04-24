package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.GamesServiceGenerator;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.games_network.GamesAPI;
import io.github.kutaiba_nezar_kashmar.newapp.BuildConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GamesRepository
{
  private static GamesRepository instance;
  private final MutableLiveData<ArrayList<Game>> allGames;
  private final MutableLiveData<ArrayList<Game>> searchedGames;
  private final MutableLiveData<Game> game;

  private GamesRepository()
  {
    allGames = new MutableLiveData<>();
    searchedGames = new MutableLiveData<>();
    game = new MutableLiveData<>();
  }

  public static synchronized GamesRepository getInstance()
  {
    if (instance == null)
    {
      instance = new GamesRepository();
    }
    return instance;
  }

  public MutableLiveData<ArrayList<Game>> getAllGames()
  {
    GamesAPI gamesAPI = GamesServiceGenerator.gamesAPI();
    Call<GamesResponse> call = gamesAPI.getAllGames(BuildConfig.RAWG_API_KEY);
    call.enqueue(new Callback<GamesResponse>()
    {
      @Override
      public void onResponse(Call<GamesResponse> call,
          Response<GamesResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            allGames.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<GamesResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return allGames;
  }

  public MutableLiveData<ArrayList<Game>> getSearchedGames(String query)
  {
    GamesAPI gamesAPI = GamesServiceGenerator.gamesAPI();
    Call<GamesResponse> call = gamesAPI
        .getSearchGames(BuildConfig.RAWG_API_KEY, query);
    call.enqueue(new Callback<GamesResponse>()
    {
      @Override
      public void onResponse(Call<GamesResponse> call,
          Response<GamesResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            searchedGames.setValue(response.body().getResults());
          }
        }
      }

      @Override
      public void onFailure(Call<GamesResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return searchedGames;
  }

  public MutableLiveData<Game> getGameById(int id)
  {
    GamesAPI gamesAPI = GamesServiceGenerator.gamesAPI();
    Call<GamesResponse> call = gamesAPI
        .getGameById(id, BuildConfig.RAWG_API_KEY);
    call.enqueue(new Callback<GamesResponse>()
    {
      @Override
      public void onResponse(Call<GamesResponse> call,
          Response<GamesResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            game.setValue(response.body().getGame());
          }
        }
      }

      @Override
      public void onFailure(Call<GamesResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return game;
  }
}
