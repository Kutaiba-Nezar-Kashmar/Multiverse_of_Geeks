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
import java.util.Calendar;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentSingleFreeToPlayGameBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameComment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GameScreenShots;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters.FreeToPlayScreenShotsAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters.GameCommentAdapter;

public class SingleFreeToPlayGameFragment extends Fragment
{
  private FragmentSingleFreeToPlayGameBinding binding;
  private FreeToPlayScreenShotsAdapter screenShotsAdapter;
  private GamesViewModel gamesViewModel;
  private GameComment gameComment;
  private GameReview gameReview;
  private final List<GameComment> comments = new ArrayList<>();
  private List<GameScreenShots> screenShots = new ArrayList<>();
  private int gameId;
  private TextView title;
  private TextView releaseDate;
  private TextView shortDescription;
  private TextView description;
  private TextView status;
  private TextView genre;
  private TextView platform;
  private TextView publisher;
  private TextView os;
  private TextView processor;
  private TextView graphics;
  private TextView memory;
  private TextView storage;
  private TextView url;
  private TextView geekRating;
  private EditText commentField;
  private ImageView poster;
  private RecyclerView screenShotRv;
  private RecyclerView commentRV;
  private RatingBar ratingBar;
  private Button commentButton;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    gamesViewModel = new ViewModelProvider(this).get(GamesViewModel.class);
    binding = FragmentSingleFreeToPlayGameBinding.inflate(inflater, container,
        false);
    View root = binding.getRoot();

    //Views
    title = root.findViewById(R.id.single_free_to_play_title);
    poster = root.findViewById(R.id.single_free_to_play_poster);
    releaseDate = root.findViewById(R.id.free_to_play_release_date);
    shortDescription = root.findViewById(R.id.free_to_play_short_description);
    description = root.findViewById(R.id.free_to_play_description);
    url = root.findViewById(R.id.free_to_play_game_url);
    screenShotRv = root.findViewById(R.id.free_to_play_screen_shots_rv);
    status = root.findViewById(R.id.free_to_play_status);
    genre = root.findViewById(R.id.free_to_play_genre);
    platform = root.findViewById(R.id.free_to_play_platform);
    publisher = root.findViewById(R.id.free_to_play_publisher);
    os = root.findViewById(R.id.free_to_play_os);
    processor = root.findViewById(R.id.free_to_play_processor);
    graphics = root.findViewById(R.id.free_to_play_graphics);
    memory = root.findViewById(R.id.free_to_play_memory);
    storage = root.findViewById(R.id.free_to_play_storage);
    ratingBar = root.findViewById(R.id.free_game_rating_bar);
    geekRating = root.findViewById(R.id.free_to_play_rating);
    commentButton = root.findViewById(R.id.free_game_post_comment_button);
    commentField = root.findViewById(R.id.free_game_comment_field);
    commentRV = root.findViewById(R.id.free_game_coming_rv_id);

    setUpRatingBar();
    getGeekAverageRating();
    checkIfSignedIn();

    return root;
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
      //Get FreeToPlay id from navigation component argument
      String id = SingleFreeToPlayGameFragmentArgs.fromBundle(getArguments())
          .getFreeToPlayId();
      gameId = Integer.parseInt(id);

      //Observe a FreeToPlay object and set values to views
      gamesViewModel.findFreeToPlayGameById(gameId)
          .observe(getViewLifecycleOwner(), freeToPlayGameResponse -> {
            title.setText(freeToPlayGameResponse.getTitle());

            //Glide to set image to poster
            Glide.with(view.getContext())
                .load(freeToPlayGameResponse.getThumbnail()).into(poster);

            releaseDate.setText(freeToPlayGameResponse.getRelease_date());
            shortDescription.setText(
                freeToPlayGameResponse.getShort_description());
            description.setText(freeToPlayGameResponse.getDescription());
            url.setText(freeToPlayGameResponse.getGame_url());
            status.setText(freeToPlayGameResponse.getStatus());
            genre.setText(freeToPlayGameResponse.getGenre());
            platform.setText(freeToPlayGameResponse.getPlatform());
            publisher.setText(freeToPlayGameResponse.getPublisher());
            os.setText(freeToPlayGameResponse.getMinimum_system_requirements()
                .getOs());
            processor.setText(
                freeToPlayGameResponse.getMinimum_system_requirements()
                    .getProcessor());
            graphics.setText(
                freeToPlayGameResponse.getMinimum_system_requirements()
                    .getGraphics());
            memory.setText(
                freeToPlayGameResponse.getMinimum_system_requirements()
                    .getMemory());
            storage.setText(
                freeToPlayGameResponse.getMinimum_system_requirements()
                    .getStorage());
            screenShots = freeToPlayGameResponse.getScreenshots();
            screenShotsAdapter.updateScreenShotList(screenShots);
          });
    }

    setUpRecyclerView(view);
    setUpCommentRV(view);
  }

  private void setUpRecyclerView(View view)
  {
    //Setup Recyclerview
    screenShotRv.hasFixedSize();
    screenShotRv.setLayoutManager(new LinearLayoutManager(view.getContext(),
        LinearLayoutManager.HORIZONTAL, false));
    screenShotsAdapter = new FreeToPlayScreenShotsAdapter(screenShots);
    screenShotRv.setAdapter(screenShotsAdapter);
  }

  private void setUpRatingBar()
  {
    ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
      gameReview = new GameReview(gameId, ratingBar.getRating());
      gamesViewModel.postReview(gameReview);
    });
  }

  private void getGeekAverageRating()
  {
    List<GameReview> gr = new ArrayList<>();
    gamesViewModel.getGameReviews()
        .observe(getViewLifecycleOwner(), gameReviews -> {
          for (GameReview review : gameReviews)
          {
            if (review.getGameId() == gameId)
            {
              gr.add(review);
            }
          }
          String averageValue = String.valueOf(
              gamesViewModel.calculateAverage(gr));
          geekRating.setText(averageValue);
        });
  }

  private void checkIfSignedIn()
  {
    gamesViewModel.getCurrentUser()
        .observe(getViewLifecycleOwner(), firebaseUser -> {
          if (firebaseUser != null)
          {
            //Set authentication required view to visible for logged in users
            ratingBar.setVisibility(View.VISIBLE);
            commentField.setVisibility(View.VISIBLE);
            commentButton.setVisibility(View.VISIBLE);
            commentRV.setVisibility(View.VISIBLE);

            //Setup listener for CommentButton
            commentButton.setOnClickListener(view -> {
              gameComment = new GameComment(gameId, firebaseUser.getUid(),
                  commentField.getText().toString(),
                  firebaseUser.getDisplayName(),
                  String.valueOf(Calendar.getInstance().getTime()));
              gamesViewModel.postComment(gameComment);
              commentField.getText().clear();
            });

          }
          else
          {
            //Set authentication required view to invisible/gone for non logged in users
            ratingBar.setVisibility(View.INVISIBLE);
            commentField.setVisibility(View.GONE);
            commentButton.setVisibility(View.GONE);
            commentRV.setVisibility(View.GONE);
          }
        });
  }

  private void setUpCommentRV(View view)
  {
    GameCommentAdapter commentAdapter = new GameCommentAdapter(comments);

    //Setup observer for a list of GameComment
    Observer<List<GameComment>> update = commentAdapter::updateGameCommentList;
    gamesViewModel.getGameComments(gameId)
        .observe(getViewLifecycleOwner(), gameComments -> {
          for (GameComment gc : gameComments)
          {
            //Set Storage reference for every gameComment in the list
            gc.setUserImage(gamesViewModel.getProfileImage(gc.getUserId()));
          }
          update.onChanged(gameComments);
        });

    //Setup comment recyclerview
    commentRV.hasFixedSize();
    commentRV.setLayoutManager(
        new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL,
            false));
    commentRV.setAdapter(commentAdapter);
  }
}
