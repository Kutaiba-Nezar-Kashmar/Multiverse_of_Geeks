package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GamesResponse;
import io.reactivex.rxjava3.core.Flowable;

public interface GamesRepository
{
  Flowable<GamesResponse> getAllGames();
  Flowable<ArrayList<Game>> getSearchedGames(String query);
  Flowable<Game> getGameById(int id);
}
