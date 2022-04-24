package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies.movies_lists;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.movie_responses.SingleMovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies.MoviesMainFragmentDirections;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies.MoviesViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies.SingleMovieFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.movies.FavoriteMovieAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.movies.MoviesAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentFavoriteMoviesBinding;

public class FavoriteMoviesFragment extends Fragment
{
  private FragmentFavoriteMoviesBinding binding;
  private MoviesViewModel viewModel;
  private FavoriteMovieAdapter moviesAdapter;
  private ArrayList<SingleMovieResponse> movies = new ArrayList<>();
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
    refreshLayout = root.findViewById(R.id.movie_fav_refresh_view);
    recyclerView = root.findViewById(R.id.favorite_movies_rv);
    //refresh();
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
    viewModel.getFavoriteMovies();
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpRecyclerView();
  }

  private void setUpRecyclerView()
  {
    moviesAdapter = new FavoriteMovieAdapter(movies);
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
