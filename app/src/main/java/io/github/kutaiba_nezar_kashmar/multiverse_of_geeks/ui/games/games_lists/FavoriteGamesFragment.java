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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentFavoriteGameBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.GamesViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters.FavoriteGameAdapter;

public class FavoriteGamesFragment extends Fragment
{
  private FragmentFavoriteGameBinding binding;
  private GamesViewModel viewModel;
  private final List<Game> games = new ArrayList<>();
  private RecyclerView recyclerView;
  private SwipeRefreshLayout swipeRefreshLayout;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    viewModel = new ViewModelProvider(this).get(GamesViewModel.class);
    binding = FragmentFavoriteGameBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    swipeRefreshLayout = root.findViewById(R.id.game_fav_refresh_view);
    recyclerView = root.findViewById(R.id.favorite_games_rv);
    refresh();
    return root;
  }

  @Override
  public void onDestroy()
  {
    super.onDestroy();
    binding = null;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    viewModel.getFavoriteGames();
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpRecyclerView();
  }

  private void setUpRecyclerView()
  {
    FavoriteGameAdapter adapter = new FavoriteGameAdapter(games);
    Observer<List<Game>> update = adapter::updateGameList;
    viewModel.getFavoriteGames().observe(getViewLifecycleOwner(), update);
    recyclerView.setAdapter(adapter);
  }

  private void refresh()
  {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      setUpRecyclerView();
      swipeRefreshLayout.setRefreshing(false);
    });
  }
}
