package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.FreeToPlayGamesServiceGenerator;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.games_network.FreeToPlayAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeToPlayGamesRepository
{
  private static FreeToPlayGamesRepository instance;
  private final MutableLiveData<ArrayList<FreeToPlayGameResponse>> allFreeToPlay;

  private FreeToPlayGamesRepository()
  {
    allFreeToPlay = new MutableLiveData<ArrayList<FreeToPlayGameResponse>>();
  }

  public static synchronized FreeToPlayGamesRepository getInstance()
  {
    if (instance == null)
    {
      instance = new FreeToPlayGamesRepository();
    }
    return instance;
  }

  public MutableLiveData<ArrayList<FreeToPlayGameResponse>> getAllFreeToPlayGames()
  {
    FreeToPlayAPI freeToPlayAPI = FreeToPlayGamesServiceGenerator
        .getFreeToPlayAPI();
    Call<ArrayList<FreeToPlayGameResponse>> call = freeToPlayAPI.getLiveFreeToPlay();
    call.enqueue(new Callback<ArrayList<FreeToPlayGameResponse>>()
    {
      @Override
      public void onResponse(Call<ArrayList<FreeToPlayGameResponse>> call,
          Response<ArrayList<FreeToPlayGameResponse>> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            System.out.println("+++++++++++++++++" + response.body());
            allFreeToPlay.setValue(response.body());
          }
        }
      }

      @Override
      public void onFailure(Call<ArrayList<FreeToPlayGameResponse>> call, Throwable t)
      {
        System.out.println("------------------------" + t.getMessage());
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return allFreeToPlay;
  }
}
