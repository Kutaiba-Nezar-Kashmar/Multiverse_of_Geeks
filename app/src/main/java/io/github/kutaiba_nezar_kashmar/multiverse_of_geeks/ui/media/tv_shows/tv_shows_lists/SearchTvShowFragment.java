package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.tv_shows_lists;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentSearchTvShowBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.TVShowsViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.TvShowsMainFragmentDirections;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.TVShowAdapter;

public class SearchTvShowFragment extends Fragment
{
  private FragmentSearchTvShowBinding binding;
  private TVShowsViewModel tvShowsViewModel;
  private TVShowAdapter tvShowAdapter;
  private final List<TvShow> tvShows = new ArrayList<>();
  private RecyclerView recyclerView;
  private SwipeRefreshLayout swipeRefreshLayout;
  private SearchView searchView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    tvShowsViewModel = new ViewModelProvider(this).get(TVShowsViewModel.class);
    binding = FragmentSearchTvShowBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    //Views
    swipeRefreshLayout = root.findViewById(R.id.search_tv_refresh_view);
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
    //Setup recyclerview
    recyclerView = view.findViewById(R.id.tv_search_rv);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    tvShowAdapter = new TVShowAdapter(tvShows);

    setUpRecyclerView();
    setUpOnClickListener(view);
  }

  private void setUpOnClickListener(View view)
  {
    recyclerView.setAdapter(tvShowAdapter);
    tvShowAdapter.setListener(tvShow -> {
      TvShowsMainFragmentDirections.ActionNavTvToNavSingleTv action = TvShowsMainFragmentDirections.actionNavTvToNavSingleTv();
      action.setTvShowId(String.valueOf(tvShow.getId()));
      Navigation.findNavController(view).navigate(action);
    });
  }

  private void setUpRecyclerView()
  {
    tvShowAdapter = new TVShowAdapter(tvShows);

    //Setup observer for a list of TvShow
    Observer<List<TvShow>> update = tvShowAdapter::updateTVShowList;
    setUpSearchView(update);
  }

  private void refresh()
  {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      setUpRecyclerView();
      swipeRefreshLayout.setRefreshing(false);
    });
  }

  private void setUpSearchView(Observer<List<TvShow>> update)
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
