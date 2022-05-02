package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.network.client.FreeToPlayGamesClient;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FreeToPlayGamesRepositoryImpl implements FreeToPlayGamesRepository
{
  private FreeToPlayGamesClient client;

  public FreeToPlayGamesRepositoryImpl()
  {
    client = new FreeToPlayGamesClient();
  }

  @Override
  public Flowable<FreeToPlayGameResponse> findFreeToPlayGame(int id)
  {
    return client.getFreeToPlayAPI().getFreeToPlayGameById(id)
        .subscribeOn(Schedulers.io()).flatMap(Flowable::just);
  }

  @Override
  public Flowable<ArrayList<AllFreeToPlayGamesResponse>> getAllFreeToPlayGames()
  {
    return client.getFreeToPlayAPI().getLiveFreeToPlay()
        .subscribeOn(Schedulers.io()).flatMap(Flowable::just);
  }
}
