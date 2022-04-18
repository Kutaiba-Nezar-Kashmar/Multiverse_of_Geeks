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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.GamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.GamesViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.games.GamesAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentAllGamesBinding;

public class AllGamesFragment extends Fragment
{
  private FragmentAllGamesBinding binding;
  private RecyclerView recyclerView;
  private ArrayList<Game> gamesResponses = new ArrayList<>();
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
  }

  private void setUpRecyclerView()
  {
    adapter = new GamesAdapter(gamesResponses);
    Observer<ArrayList<Game>> update = adapter::updateGameList;
    gamesViewModel.getAllGames().observe(getViewLifecycleOwner(), update);
    recyclerView.setAdapter(adapter);
  }
}
