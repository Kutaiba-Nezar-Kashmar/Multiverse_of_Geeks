package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.games_lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Game;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.GamesViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.MainGamesFragmentDirections;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters.GamesAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentSearchGamesBinding;

public class SearchedGamesFragment extends Fragment
{
  private FragmentSearchGamesBinding binding;
  private RecyclerView recyclerView;
  private final List<Game> games = new ArrayList<Game>();
  private GamesViewModel viewModel;
  private GamesAdapter gamesAdapter;
  private SwipeRefreshLayout swipeRefreshLayout;
  private SearchView searchView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    viewModel = new ViewModelProvider(this).get(GamesViewModel.class);
    binding = FragmentSearchGamesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    swipeRefreshLayout = root.findViewById(R.id.search_game_refresh_view);
    searchView = root.findViewById(R.id.game_search_view);
    refresh();
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
    recyclerView = view.findViewById(R.id.game_search_rv);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    gamesAdapter = new GamesAdapter(games);
    setUpRecyclerView();
    setUpOnClickListener(view);
  }

  private void setUpOnClickListener(View view)
  {
    recyclerView.setAdapter(gamesAdapter);
    gamesAdapter.setListener(game -> {
      MainGamesFragmentDirections.ActionNavGamesToSingleGameNave action = MainGamesFragmentDirections
          .actionNavGamesToSingleGameNave();
      action.setGameId(String.valueOf(game.getId()));
      Navigation.findNavController(view).navigate(action);
    });
  }

  private void setUpRecyclerView()
  {
    gamesAdapter = new GamesAdapter(games);
    Observer<List<Game>> update = gamesAdapter::updateGameList;
    setUpSearchView(update);
  }

  private void refresh()
  {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      swipeRefreshLayout.setRefreshing(false);
    });
  }

  private void setUpSearchView(Observer<List<Game>> update)
  {
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
    {
      @Override
      public boolean onQueryTextSubmit(String query)
      {
        viewModel.getSearchedGames(query)
            .observe(getViewLifecycleOwner(), update);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String query)
      {
        viewModel.getSearchedGames(query)
            .observe(getViewLifecycleOwner(), update);
        return false;
      }
    });
  }
}
