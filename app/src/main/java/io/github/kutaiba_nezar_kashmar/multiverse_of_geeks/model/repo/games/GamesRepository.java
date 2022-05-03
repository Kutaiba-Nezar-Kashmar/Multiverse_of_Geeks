package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GamesResponse;
import io.reactivex.rxjava3.core.Flowable;

public interface GamesRepository
{
  Flowable<GamesResponse> getAllGames();
  Flowable<ArrayList<Game>> getSearchedGames(String query);
  Flowable<Game> getGameById(int id);
  LiveData<List<Game>> getFavoriteGames();
  LiveData<Game> getSingleFavoriteGame(int id);
  void insertFavoriteGame(Game game);
  void deleteFavoriteGame(Game game);
}
