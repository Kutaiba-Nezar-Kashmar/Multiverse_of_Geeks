package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.movies_lists;

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
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentFavoriteMoviesBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.MoviesViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters.FavoriteMovieAdapter;

public class FavoriteMoviesFragment extends Fragment
{
  private FragmentFavoriteMoviesBinding binding;
  private MoviesViewModel viewModel;
  private final List<SingleMovieResponse> movies = new ArrayList<>();
  private RecyclerView recyclerView;
  private SwipeRefreshLayout refreshLayout;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    viewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
    binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    //Views
    refreshLayout = root.findViewById(R.id.movie_fav_refresh_view);
    recyclerView = root.findViewById(R.id.favorite_movies_rv);

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
    //Setup recyclerview initialization
    viewModel.getFavoriteMovies();
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    setUpRecyclerView();
  }

  private void setUpRecyclerView()
  {
    FavoriteMovieAdapter moviesAdapter = new FavoriteMovieAdapter(movies);

    //Setup observer for a list of SingleMovieResponse
    Observer<List<SingleMovieResponse>> update = moviesAdapter::updateMovieList;
    viewModel.getFavoriteMovies().observe(getViewLifecycleOwner(), update);
    recyclerView.setAdapter(moviesAdapter);
  }

  private void refresh()
  {
    refreshLayout.setOnRefreshListener(() -> {
      setUpRecyclerView();
      refreshLayout.setRefreshing(false);
    });
  }
}
