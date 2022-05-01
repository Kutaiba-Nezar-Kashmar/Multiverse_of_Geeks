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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.GamesViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.MainGamesFragmentDirections;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters.FreeToPlayAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentFreeToPlayGamesBinding;

public class FreeToPlayGamesFragment extends Fragment
{
  private FragmentFreeToPlayGamesBinding binding;
  private RecyclerView recyclerView;
  private final ArrayList<AllFreeToPlayGamesResponse> freeToPlayGames = new ArrayList<>();
  private GamesViewModel gamesViewModel;
  private FreeToPlayAdapter freeToPlayAdapter;
  private SwipeRefreshLayout swipeRefreshLayout;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    gamesViewModel = new ViewModelProvider(this).get(GamesViewModel.class);
    binding = FragmentFreeToPlayGamesBinding
        .inflate(inflater, container, false);
    View root = binding.getRoot();
    swipeRefreshLayout = root.findViewById(R.id.free_to_play_refresh_view);
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
    gamesViewModel.getAllFreeToPlay();

    recyclerView = view.findViewById(R.id.free_to_play_rv);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpRecyclerView();
    setOnClickListener(view);
  }

  private void setUpRecyclerView()
  {
    freeToPlayAdapter = new FreeToPlayAdapter(freeToPlayGames);
    Observer<ArrayList<AllFreeToPlayGamesResponse>> update = freeToPlayAdapter::updateFreeToPlayList;
    gamesViewModel.getAllFreeToPlay().observe(getViewLifecycleOwner(), update);
    recyclerView.setAdapter(freeToPlayAdapter);
  }

  private void refresh()
  {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      setUpRecyclerView();
      swipeRefreshLayout.setRefreshing(false);
    });
  }

  private void setOnClickListener(View view)
  {
    freeToPlayAdapter.setListener(allFreeToPlayGamesResponse -> {
      MainGamesFragmentDirections.ActionNavGamesToSingleFreeToPlayNav action = MainGamesFragmentDirections
          .actionNavGamesToSingleFreeToPlayNav();
      action
          .setFreeToPlayId(String.valueOf(allFreeToPlayGamesResponse.getId()));
      Navigation.findNavController(view).navigate(action);
    });
  }
}
