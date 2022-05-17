package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import java.util.Objects;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameComment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.GamesRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.GamesRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.free_to_play.FreeToPlayGamesRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.games.free_to_play.FreeToPlayGamesRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.game.GameCommentRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.game.GameCommentRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.game.GameReviewRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.game.GameReviewRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserStorageRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserStorageRepositoryImpl;

public class GamesViewModel extends AndroidViewModel
{
  private final FreeToPlayGamesRepository freeToPlayGamesRepository;
  private final GamesRepository gamesRepository;
  private final UserRepository userRepository;
  private final GameReviewRepository gameReviewRepository;
  private final GameCommentRepository gameCommentRepository;
  private final UserStorageRepository userStorageRepository;

  public GamesViewModel(@NonNull Application application)
  {
    super(application);
    freeToPlayGamesRepository = FreeToPlayGamesRepositoryImpl.getInstance();
    gamesRepository = GamesRepositoryImpl.getInstance(application);
    userRepository = UserRepositoryImpl.getInstance(application);
    gameReviewRepository = GameReviewRepositoryImpl.getInstance();
    gameCommentRepository = GameCommentRepositoryImpl.getInstance();
    userStorageRepository = UserStorageRepositoryImpl.getInstance();
  }

  public LiveData<FirebaseUser> getCurrentUser()
  {
    return userRepository.getCurrentUser();
  }

  public StorageReference getProfileImage(String userId)
  {
    return userStorageRepository.getUserProfileImage(userId);
  }

  public void postReview(GameReview gameReview)
  {
    String userId = Objects.requireNonNull(
        userRepository.getCurrentUser().getValue()).getUid();
    gameReviewRepository.postReview(gameReview, userId);
  }

  public void postComment(GameComment gameComment)
  {
    String userId = Objects.requireNonNull(
        userRepository.getCurrentUser().getValue()).getUid();
    gameCommentRepository.postComment(gameComment, userId);
  }

  public LiveData<List<GameComment>> getGameComments(int gameId)
  {
    return gameCommentRepository.gameComments(gameId);
  }

  public LiveData<List<GameReview>> getGameReviews()
  {
    return gameReviewRepository.getGameReviews();
  }

  public float calculateAverage(List<GameReview> gameReviews)
  {
    //Takes the rating of every game in the data base and calculate average
    float total = 0;
    for (GameReview gr : gameReviews)
    {
      total += gr.getGameRating();
    }
    return total / gameReviews.size();
  }

  public LiveData<List<Game>> getFavoriteGames()
  {
    return gamesRepository.getFavoriteGames();
  }

  public LiveData<Game> getSingleFavoriteGame(int id)
  {
    return gamesRepository.getSingleFavoriteGame(id);
  }

  public void insertFavoriteGame(Game game)
  {
    gamesRepository.insertFavoriteGame(game);
  }

  public void deleteFavoriteGame(Game game)
  {
    gamesRepository.deleteFavoriteGame(game);
  }

  public LiveData<List<AllFreeToPlayGamesResponse>> getAllFreeToPlay()
  {
    return freeToPlayGamesRepository.getAllFreeToPlayGames();
  }

  public LiveData<FreeToPlayGameResponse> findFreeToPlayGameById(int id)
  {
    return freeToPlayGamesRepository.findFreeToPlayGame(id);
  }

  public LiveData<List<Game>> getAllGames()
  {
    return gamesRepository.getAllGames();
  }

  public LiveData<Game> getGameById(int id)
  {
    return gamesRepository.getGameById(id);
  }

  public LiveData<List<Game>> getSearchedGames(String query)
  {
    return gamesRepository.getSearchedGames(query);
  }
}
