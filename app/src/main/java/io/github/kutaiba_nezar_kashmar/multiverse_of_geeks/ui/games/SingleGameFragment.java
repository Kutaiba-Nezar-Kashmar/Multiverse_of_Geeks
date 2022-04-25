package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GameGenreResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GameTageResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GamesDevelopersResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.PlatformsResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.games.DeveloperAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.games.PlatformAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentSingleGameBinding;

public class SingleGameFragment extends Fragment
{
  private FragmentSingleGameBinding binding;
  private GamesViewModel viewModel;
  private DeveloperAdapter developerAdapter;
  private PlatformAdapter platformAdapter;
  private ArrayList<GamesDevelopersResponse> developersResponses = new ArrayList<>();
  private ArrayList<PlatformsResponse> platformsResponses = new ArrayList<>();
  private int id;
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
      String id = SingleGameFragmentArgs.fromBundle(getArguments()).getGameId();
      this.id = Integer.parseInt(id);
      viewModel.getGameById(this.id).observe(getViewLifecycleOwner(), game -> {
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
    viewModel.getSingleFavoriteGame(id)
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
}
