package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentSingleGameBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameComment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GameGenreResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GameTageResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GamesDevelopersResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.PlatformsResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters.DeveloperAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters.GameCommentAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters.PlatformAdapter;

public class SingleGameFragment extends Fragment
{
  private FragmentSingleGameBinding binding;
  private GamesViewModel viewModel;
  private GameCommentAdapter commentAdapter;
  private GameComment gameComment;
  private List<GameComment> comments = new ArrayList<>();
  private DeveloperAdapter developerAdapter;
  private PlatformAdapter platformAdapter;
  private List<GamesDevelopersResponse> developersResponses = new ArrayList<>();
  private List<PlatformsResponse> platformsResponses = new ArrayList<>();
  private GameReview gameReview;
  private int gameId;
  private TextView title;
  private ImageView poster;
  private TextView releaseDate;
  private TextView rating;
  private TextView playTime;
  private TextView lastUpdate;
  private TextView genre;
  private TextView tags;
  private TextView age;
  private RecyclerView developersRv;
  private RecyclerView platformRv;
  private Button favButton;
  private RatingBar ratingBar;
  private TextView geekRating;
  private RecyclerView commentRV;
  private Button commentButton;
  private EditText commentField;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    viewModel = new ViewModelProvider(this).get(GamesViewModel.class);
    binding = FragmentSingleGameBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    favButton = root.findViewById(R.id.game_fav_button_id);
    title = root.findViewById(R.id.single_game_title);
    poster = root.findViewById(R.id.single_game_poster);
    releaseDate = root.findViewById(R.id.single_game_release_date);
    rating = root.findViewById(R.id.single_game_rating);
    playTime = root.findViewById(R.id.single_game_play_time);
    lastUpdate = root.findViewById(R.id.single_game_last_update);
    genre = root.findViewById(R.id.single_game_genre);
    tags = root.findViewById(R.id.single_game_tags);
    age = root.findViewById(R.id.single_game_age);
    developersRv = root.findViewById(R.id.single_game_developers_rv);
    platformRv = root.findViewById(R.id.game_platform_rv);
    ratingBar = root.findViewById(R.id.game_rating_bar);
    geekRating = root.findViewById(R.id.geek_game_rating);
    commentButton = root.findViewById(R.id.game_post_comment_button);
    commentField = root.findViewById(R.id.game_comment_field);
    commentRV = root.findViewById(R.id.game_coming_rv_id);
    setUpRatingBar();
    getGeekAverageRating();
    checkIfSignedIn();
    setUpCommentButton();
    return root;
  }

  private void setUpCommentButton()
  {
    commentButton.setOnClickListener(view -> {
      gameComment = new GameComment(gameId, commentField.getText().toString());
      viewModel.postComment(gameComment);
      commentField.getText().clear();
    });
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    if (getArguments() != null)
    {
      String id = SingleGameFragmentArgs.fromBundle(getArguments()).getGameId();
      this.gameId = Integer.parseInt(id);
      viewModel.getGameById(this.gameId)
          .observe(getViewLifecycleOwner(), game -> {
            title.setText(game.getName());
            Glide.with(view.getContext()).load(game.getBackground_image())
                .into(poster);
            releaseDate.setText(game.getReleased());
            rating.setText(String.valueOf(game.getRating()));
            playTime.setText(String.valueOf(game.getPlaytime()));
            lastUpdate.setText(game.getUpdated());
            for (GameGenreResponse gen : game.getGenres())
            {
              genre.append(gen.getName() + "\n");
            }
            for (GameTageResponse tag : game.getTags())
            {
              tags.append(tag.getName() + "\n");
            }
            age.setText(game.getEsrb_rating().getName());
            developersResponses = game.getDevelopers();
            developerAdapter.updateDeveloperList(developersResponses);
            platformsResponses = game.getPlatforms();
            platformAdapter.updatePlatformList(platformsResponses);
            setUpFavorite(game);
          });
    }
    setDevelopersRv(view);
    setPlatformRv(view);
    setUpCommentRV(view);
  }

  private void setDevelopersRv(View view)
  {
    developersRv.hasFixedSize();
    developersRv.setLayoutManager(new LinearLayoutManager(view.getContext(),
        LinearLayoutManager.HORIZONTAL, false));
    developerAdapter = new DeveloperAdapter(developersResponses);
    developersRv.setAdapter(developerAdapter);
  }

  private void setPlatformRv(View view)
  {
    platformRv.hasFixedSize();
    platformRv.setLayoutManager(new LinearLayoutManager(view.getContext(),
        LinearLayoutManager.HORIZONTAL, false));
    platformAdapter = new PlatformAdapter(platformsResponses);
    platformRv.setAdapter(platformAdapter);
  }

  private void setUpFavorite(Game game)
  {
    viewModel.getSingleFavoriteGame(gameId)
        .observe(getViewLifecycleOwner(), favGame -> {
          if (favGame != null)
          {
            favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
            favButton.setOnClickListener(view -> {
              viewModel.deleteFavoriteGame(game);
            });
          }
          else
          {
            favButton.setBackgroundResource(R.drawable.fav_border_ic);
            favButton.setOnClickListener(view -> {
              viewModel.insertFavoriteGame(game);
            });
          }
        });
  }

  private void setUpRatingBar()
  {
    ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
      gameReview = new GameReview(gameId, ratingBar.getRating());
      viewModel.postReview(gameReview);
    });
  }

  private void getGeekAverageRating()
  {
    List<GameReview> gr = new ArrayList<>();
    viewModel.getGameReviews().observe(getViewLifecycleOwner(), gameReviews -> {
      for (GameReview review : gameReviews)
      {
        if (review.getGameId() == gameId)
        {
          gr.add(review);
        }
      }
      String averageValue = String.valueOf(viewModel.calculateAverage(gr));
      geekRating.setText(averageValue);
    });
  }

  private void checkIfSignedIn()
  {
    viewModel.getCurrentUser()
        .observe(getViewLifecycleOwner(), firebaseUser -> {
          if (firebaseUser != null)
          {
            ratingBar.setVisibility(View.VISIBLE);
            commentField.setVisibility(View.VISIBLE);
            commentButton.setVisibility(View.VISIBLE);
          }
          else
          {
            ratingBar.setVisibility(View.INVISIBLE);
            commentField.setVisibility(View.GONE);
            commentButton.setVisibility(View.GONE);
          }
        });
  }

  private void setUpCommentRV(View view)
  {
    commentAdapter = new GameCommentAdapter(comments);
    Observer<List<GameComment>> update = commentAdapter::updateGameCommentList;
    viewModel.getGameComments(gameId).observe(getViewLifecycleOwner(), update);
    commentRV.hasFixedSize();
    commentRV.setLayoutManager(
        new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL,
            false));
    commentRV.setAdapter(commentAdapter);
  }
}
