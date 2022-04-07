package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.TVShowAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentTvShowsBinding;

public class TvShowsFragment extends Fragment
{
  private FragmentTvShowsBinding binding;
  private TVShowsViewModel tvShowsViewModel;
  private RecyclerView recyclerView;
  private ArrayList<TvShow> tvShows = new ArrayList<>();
  private TVShowAdapter adapter;
  private SwipeRefreshLayout swipeRefreshLayout;
  private Button popularButton;
  private Button topButton;
  private Button nowButton;
  private Button upButton;
  private SearchView searchView;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    tvShowsViewModel = new ViewModelProvider(this).get(TVShowsViewModel.class);

    binding = FragmentTvShowsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    swipeRefreshLayout = root.findViewById(R.id.tv_refresh_view);
    popularButton = root.findViewById(R.id.popular_tv_button);
    topButton = root.findViewById(R.id.top_tv_button);
    nowButton = root.findViewById(R.id.now_tv_button);
    upButton = root.findViewById(R.id.up_tv_button);
    searchView = root.findViewById(R.id.tv_search_view);

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
    tvShowsViewModel.getAllPopularTvShows();

    recyclerView = view.findViewById(R.id.tv_rv);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpRecyclerView();
    setUpOnClickListener(view);
  }

  private void setUpRecyclerView()
  {
    adapter = new TVShowAdapter(tvShows);
    Observer<ArrayList<TvShow>> update = adapter::updateTVShowList;
    tvShowsViewModel.getAllPopularTvShows()
        .observe(getViewLifecycleOwner(), update);

    popularButton.setOnClickListener(view -> {
      tvShowsViewModel.getAllPopularTvShows()
          .observe(getViewLifecycleOwner(), update);
    });

    topButton.setOnClickListener(view -> {
      tvShowsViewModel.getAllTopRatedTvShows()
          .observe(getViewLifecycleOwner(), update);
    });

    nowButton.setOnClickListener(view -> {
      tvShowsViewModel.getAllOnAirTvShows()
          .observe(getViewLifecycleOwner(), update);
    });

    upButton.setOnClickListener(view -> {
      tvShowsViewModel.getAllAiringTodayTvShows()
          .observe(getViewLifecycleOwner(), update);
    });

    setUpSearchView(update);
  }

  private void setUpOnClickListener(View view)
  {
    recyclerView.setAdapter(adapter);
    adapter.setListener(tvShow -> {
      TvShowsFragmentDirections.ActionNavTvToNavSingleTv action = TvShowsFragmentDirections
          .actionNavTvToNavSingleTv();
      action.setTvShowId(String.valueOf(tvShow.getId()));
      Navigation.findNavController(view).navigate(action);
    });
  }

  private void refresh()
  {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      setUpRecyclerView();
      swipeRefreshLayout.setRefreshing(false);
    });
  }

  private void setUpSearchView(Observer<ArrayList<TvShow>> update)
  {
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
    {
      @Override
      public boolean onQueryTextSubmit(String query)
      {
        tvShowsViewModel.getAllSearchedTvShows(query)
            .observe(getViewLifecycleOwner(), update);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String query)
      {
        tvShowsViewModel.getAllSearchedTvShows(query)
            .observe(getViewLifecycleOwner(), update);
        return false;
      }
    });
  }
}
