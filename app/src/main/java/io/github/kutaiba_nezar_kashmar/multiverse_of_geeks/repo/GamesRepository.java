package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.dao.GeekDatabase;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.dao.games.GameDAO;
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
  private final ExecutorService executorService;
  private final GameDAO gameDAO;
  private final LiveData<List<Game>> favoriteGames;
  private final LiveData<Game> singleFavoriteGame;
  private final MutableLiveData<ArrayList<Game>> allGames;
  private final MutableLiveData<ArrayList<Game>> searchedGames;
  private final MutableLiveData<Game> game;

  private GamesRepository(Application application)
  {
    GeekDatabase database = GeekDatabase.getInstance(application);
    gameDAO = database.gameDAO();
    allGames = new MutableLiveData<>();
    searchedGames = new MutableLiveData<>();
    game = new MutableLiveData<>();
    executorService = Executors.newFixedThreadPool(2);
    favoriteGames = gameDAO.getAllFavoriteGames();
    singleFavoriteGame = new MutableLiveData<>();
  }

  public static synchronized GamesRepository getInstance(
      Application application)
  {
    if (instance == null)
    {
      instance = new GamesRepository(application);
    }
    return instance;
  }

  public LiveData<List<Game>> getFavoriteGames()
  {
    return favoriteGames;
  }

  public LiveData<Game> getSingleFavoriteGame(int id)
  {
    return gameDAO.getGameById(id);
  }

  public void insertFavoriteGame(Game game)
  {
    executorService.execute(() -> gameDAO.insertGame(game));
  }

  public void deleteFavoriteGame(Game game)
  {
    executorService.execute(() -> gameDAO.deleteGame(game));
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
