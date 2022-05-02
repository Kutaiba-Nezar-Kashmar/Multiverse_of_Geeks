package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.FreeToPlayGamesClient;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.games_network.FreeToPlayAPI;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.ResourceSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeToPlayGamesRepository
{
  private static FreeToPlayGamesRepository instance;
  //private final MutableLiveData<ArrayList<AllFreeToPlayGamesResponse>> allFreeToPlay;
  private final MutableLiveData<FreeToPlayGameResponse> freeToPlaygameById;

  private FreeToPlayGamesRepository()
  {
    //allFreeToPlay = new MutableLiveData<>();
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

  public Flowable<ArrayList<AllFreeToPlayGamesResponse>> getAllFreeToPlayGames()
  {
    return FreeToPlayGamesClient.getFreeToPlayAPI().getLiveFreeToPlay()
        .subscribeOn(Schedulers.io()).flatMap(Flowable::just);
  }
}
