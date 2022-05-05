package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.movies_lists;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.MoviesMainFragmentDirections;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.MoviesViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters.MoviesAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentSerachMoviesBinding;

public class SearchedMoviesFragment extends Fragment
{
  private FragmentSerachMoviesBinding binding;
  private RecyclerView recyclerView;
  private final List<Movie> movies = new ArrayList<>();
  private MoviesViewModel moviesViewModel;
  private MoviesAdapter moviesAdapter;
  private SwipeRefreshLayout swipeRefreshLayout;
  private SearchView searchView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
    binding = FragmentSerachMoviesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    swipeRefreshLayout = root.findViewById(R.id.search_movies_refresh_view);
    searchView = root.findViewById(R.id.movies_search_view);
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
    recyclerView = view.findViewById(R.id.search_movies_rv);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    moviesAdapter = new MoviesAdapter(movies);
    setUpRecyclerView();
    setUpOnClickListener(view);
  }

  private void setUpOnClickListener(View view)
  {
    recyclerView.setAdapter(moviesAdapter);
    moviesAdapter.setListener(movie -> {
      MoviesMainFragmentDirections.ActionNavMainMoviesToNavSingleMovie action = MoviesMainFragmentDirections
          .actionNavMainMoviesToNavSingleMovie();
      action.setMovieIdArg(String.valueOf(movie.getId()));
      Navigation.findNavController(view).navigate(action);
    });
  }

  private void setUpRecyclerView()
  {
    moviesAdapter = new MoviesAdapter(movies);
    Observer<List<Movie>> update = moviesAdapter::updateMovieList;
    setUpSearchView(update);
  }

  private void refresh()
  {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      swipeRefreshLayout.setRefreshing(false);
    });
  }

  public void setUpSearchView(Observer<List<Movie>> update)
  {
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
    {
      @Override
      public boolean onQueryTextSubmit(String query)
      {
        moviesViewModel.getAllSearchedMoviesMovies(query)
            .observe(getViewLifecycleOwner(), update);
        return false;
      }

      @Override
      public boolean onQueryTextChange(String query)
      {
        moviesViewModel.getAllSearchedMoviesMovies(query)
            .observe(getViewLifecycleOwner(), update);
        return false;
      }
    });
  }
}
