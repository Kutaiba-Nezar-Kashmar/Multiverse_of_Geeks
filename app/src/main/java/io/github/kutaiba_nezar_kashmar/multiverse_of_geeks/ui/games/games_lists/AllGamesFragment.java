package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.games_lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentAllGamesBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.GamesViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.MainGamesFragmentDirections;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters.GamesAdapter;

public class AllGamesFragment extends Fragment
{
  private FragmentAllGamesBinding binding;
  private RecyclerView recyclerView;
  private List<Game> gamesResponses = new ArrayList<Game>();
  private GamesViewModel gamesViewModel;
  private GamesAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    gamesViewModel = new ViewModelProvider(this).get(GamesViewModel.class);
    binding = FragmentAllGamesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    recyclerView = root.findViewById(R.id.all_games_rv);
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
    gamesViewModel.getAllGames();

    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpRecyclerView();
    setOnClickListener(view);
  }

  private void setUpRecyclerView()
  {
    adapter = new GamesAdapter(gamesResponses);
    Observer<List<Game>> update = adapter::updateGameList;
    gamesViewModel.getAllGames().observe(getViewLifecycleOwner(), update);
    recyclerView.setAdapter(adapter);
  }

  private void setOnClickListener(View view)
  {
    adapter.setListener(game -> {
      MainGamesFragmentDirections.ActionNavGamesToSingleGameNave action = MainGamesFragmentDirections
          .actionNavGamesToSingleGameNave();
      action.setGameId(String.valueOf(game.getId()));
      Navigation.findNavController(view).navigate(action);
    });
  }
}
