package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.games;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.FreeToPlayGamesServiceGenerator;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.games_network.FreeToPlayAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeToPlayGamesRepository
{
  private static FreeToPlayGamesRepository instance;
  private final MutableLiveData<ArrayList<AllFreeToPlayGamesResponse>> allFreeToPlay;
  private final MutableLiveData<FreeToPlayGameResponse> freeToPlaygameById;

  private FreeToPlayGamesRepository()
  {
    allFreeToPlay = new MutableLiveData<>();
    freeToPlaygameById = new MutableLiveData<>();
  }

  public static synchronized FreeToPlayGamesRepository getInstance()
  {
    if (instance == null)
    {
      instance = new FreeToPlayGamesRepository();
    }
    return instance;
  }

  public MutableLiveData<FreeToPlayGameResponse> findFreeToPlayGame(int id)
  {
    FreeToPlayAPI freeToPlayAPI = FreeToPlayGamesServiceGenerator
        .getFreeToPlayAPI();
    Call<FreeToPlayGameResponse> call = freeToPlayAPI.getFreeToPlayGameById(id);
    call.enqueue(new Callback<FreeToPlayGameResponse>()
    {
      @Override
      public void onResponse(Call<FreeToPlayGameResponse> call,
          Response<FreeToPlayGameResponse> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            freeToPlaygameById.setValue(response.body());
          }
        }
      }

      @Override
      public void onFailure(Call<FreeToPlayGameResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return freeToPlaygameById;
  }

  public MutableLiveData<ArrayList<AllFreeToPlayGamesResponse>> getAllFreeToPlayGames()
  {
    FreeToPlayAPI freeToPlayAPI = FreeToPlayGamesServiceGenerator
        .getFreeToPlayAPI();
    Call<ArrayList<AllFreeToPlayGamesResponse>> call = freeToPlayAPI
        .getLiveFreeToPlay();
    call.enqueue(new Callback<ArrayList<AllFreeToPlayGamesResponse>>()
    {
      @Override
      public void onResponse(Call<ArrayList<AllFreeToPlayGamesResponse>> call,
          Response<ArrayList<AllFreeToPlayGamesResponse>> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            allFreeToPlay.setValue(response.body());
          }
        }
      }

      @Override
      public void onFailure(Call<ArrayList<AllFreeToPlayGamesResponse>> call,
          Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return allFreeToPlay;
  }
}
