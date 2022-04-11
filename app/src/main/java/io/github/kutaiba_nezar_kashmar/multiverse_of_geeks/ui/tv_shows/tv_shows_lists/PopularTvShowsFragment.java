package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows.tv_shows_lists;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows.TVShowsViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows.TvShowsMainFragmentDirections;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.tv_show.TVShowAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentPopularTvShowsBinding;

public class PopularTvShowsFragment extends Fragment
{
  private FragmentPopularTvShowsBinding binding;
  private TVShowsViewModel tvShowsViewModel;
  private RecyclerView recyclerView;
  private final ArrayList<TvShow> tvShows = new ArrayList<>();
  private TVShowAdapter adapter;
  private SwipeRefreshLayout swipeRefreshLayout;
  private SearchView searchView;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    tvShowsViewModel = new ViewModelProvider(this).get(TVShowsViewModel.class);
    binding = FragmentPopularTvShowsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    swipeRefreshLayout = root.findViewById(R.id.popular_tv_refresh_view);
    searchView = root.findViewById(R.id.popular_tv_search_view);
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

    recyclerView = view.findViewById(R.id.popular_tv_rv);
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
    setUpSearchView(update);
  }

  private void setUpOnClickListener(View view)
  {
    recyclerView.setAdapter(adapter);
    adapter.setListener(tvShow -> {
      TvShowsMainFragmentDirections.ActionNavTvToNavSingleTv action = TvShowsMainFragmentDirections
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
