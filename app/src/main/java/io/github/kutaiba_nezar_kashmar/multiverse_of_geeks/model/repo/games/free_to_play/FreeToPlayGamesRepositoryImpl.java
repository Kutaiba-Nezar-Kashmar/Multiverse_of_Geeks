package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.free_to_play;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.FreeToPlayGamesClient;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.games_network.FreeToPlayAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeToPlayGamesRepositoryImpl implements FreeToPlayGamesRepository
{
  private static FreeToPlayGamesRepository instance;
  private final MutableLiveData<FreeToPlayGameResponse> freeToPlayGame;
  private final MutableLiveData<List<AllFreeToPlayGamesResponse>> freeToPlayGames;

  private FreeToPlayGamesRepositoryImpl()
  {
    freeToPlayGame = new MutableLiveData<>();
    freeToPlayGames = new MutableLiveData<>();
  }

  public static synchronized FreeToPlayGamesRepository getInstance()
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
      public void onResponse(@NonNull Call<FreeToPlayGameResponse> call,
          @NonNull Response<FreeToPlayGameResponse> response)
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
      public void onFailure(@NonNull Call<FreeToPlayGameResponse> call,
          @NonNull Throwable t)
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
    Call<List<AllFreeToPlayGamesResponse>> call = freeToPlayAPI.getLiveFreeToPlay();
    call.enqueue(new Callback<List<AllFreeToPlayGamesResponse>>()
    {
      @Override
      public void onResponse(
          @NonNull Call<List<AllFreeToPlayGamesResponse>> call,
          @NonNull Response<List<AllFreeToPlayGamesResponse>> response)
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
      public void onFailure(
          @NonNull Call<List<AllFreeToPlayGamesResponse>> call,
          @NonNull Throwable t)
      {
        Log.i("Retrofit", "Something went wrong :(");

      }
    });
    return freeToPlayGames;
  }
}
