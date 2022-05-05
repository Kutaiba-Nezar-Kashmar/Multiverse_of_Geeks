package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.FreeToPlayGamesClient;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.MediaClient;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.games_network.FreeToPlayAPI;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeToPlayGamesRepositoryImpl implements FreeToPlayGamesRepository
{
  private static FreeToPlayGamesRepositoryImpl instance;
  private FreeToPlayGamesClient client;
  private final MutableLiveData<FreeToPlayGameResponse> freeToPlayGame;
  private final MutableLiveData<List<AllFreeToPlayGamesResponse>> freeToPlayGames;

  private FreeToPlayGamesRepositoryImpl()
  {
    client = new FreeToPlayGamesClient();
    freeToPlayGame = new MutableLiveData<>();
    freeToPlayGames = new MutableLiveData<>();
  }

  public static synchronized FreeToPlayGamesRepositoryImpl getInstance()
  {
    if (instance == null)
    {
      instance = new FreeToPlayGamesRepositoryImpl();
    }
    return instance;
  }

  @Override
  public MutableLiveData<FreeToPlayGameResponse> findFreeToPlayGame(int id)
  {
    FreeToPlayAPI freeToPlayAPI = FreeToPlayGamesClient.getFreeToPlayAPI();
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
            freeToPlayGame.setValue(response.body());
          }
        }
      }

      @Override
      public void onFailure(Call<FreeToPlayGameResponse> call, Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");
      }
    });
    return freeToPlayGame;
  }

  @Override
  public MutableLiveData<List<AllFreeToPlayGamesResponse>> getAllFreeToPlayGames()
  {
    FreeToPlayAPI freeToPlayAPI = FreeToPlayGamesClient.getFreeToPlayAPI();
    Call<List<AllFreeToPlayGamesResponse>> call = freeToPlayAPI
        .getLiveFreeToPlay();
    call.enqueue(new Callback<List<AllFreeToPlayGamesResponse>>()
    {
      @Override
      public void onResponse(Call<List<AllFreeToPlayGamesResponse>> call,
          Response<List<AllFreeToPlayGamesResponse>> response)
      {
        if (response.isSuccessful())
        {
          if (response.body() != null)
          {
            freeToPlayGames.setValue(response.body());
          }
        }
      }

      @Override
      public void onFailure(Call<List<AllFreeToPlayGamesResponse>> call,
          Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");

      }
    });
    return freeToPlayGames;
  }
}
