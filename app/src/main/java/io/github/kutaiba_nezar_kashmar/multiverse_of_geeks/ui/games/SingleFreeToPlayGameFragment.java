package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.FreeToPlayGameScreenShots;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.games.FreeToPlayScreenShotsAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentSingleFreeToPlayGameBinding;

public class SingleFreeToPlayGameFragment extends Fragment
{
  private FragmentSingleFreeToPlayGameBinding binding;
  private FreeToPlayScreenShotsAdapter screenShotsAdapter;
  private GamesViewModel gamesViewModel;
  private ArrayList<FreeToPlayGameScreenShots> screenShots = new ArrayList<>();
  private int gameId;
  private TextView title;
  private ImageView poster;
  private TextView releaseDate;
  private TextView shortDescription;
  private TextView description;
  private TextView url;
  private RecyclerView screenShotRv;
  private TextView status;
  private TextView genre;
  private TextView platform;
  private TextView publisher;
  private TextView os;
  private TextView processor;
  private TextView graphics;
  private TextView memory;
  private TextView storage;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    gamesViewModel = new ViewModelProvider(this).get(GamesViewModel.class);
    binding = FragmentSingleFreeToPlayGameBinding
        .inflate(inflater, container, false);
    View root = binding.getRoot();
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
      String id = SingleFreeToPlayGameFragmentArgs.fromBundle(getArguments())
          .getFreeToPlayId();
      gameId = Integer.parseInt(id);
      gamesViewModel.findFreeToPlayGameById(gameId)
          .observe(getViewLifecycleOwner(), freeToPlayGameResponse -> {
            title.setText(freeToPlayGameResponse.getTitle());
            Glide.with(view.getContext())
                .load(freeToPlayGameResponse.getThumbnail()).into(poster);
            releaseDate.setText(freeToPlayGameResponse.getRelease_date());
            shortDescription
                .setText(freeToPlayGameResponse.getShort_description());
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
  }

  private void setUpRecyclerView(View view)
  {
    screenShotRv.hasFixedSize();
    screenShotRv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
    screenShotsAdapter = new FreeToPlayScreenShotsAdapter(screenShots);
    screenShotRv.setAdapter(screenShotsAdapter);
  }
}
