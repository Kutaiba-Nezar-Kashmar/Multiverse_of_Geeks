package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.MoviesAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentTopRatedMoviesBinding;

public class TopRatedMoviesFragment extends Fragment
{
  private FragmentTopRatedMoviesBinding binding;
  private RecyclerView recyclerView;
  private final ArrayList<Movie> movies = new ArrayList<>();
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
    binding = FragmentTopRatedMoviesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    swipeRefreshLayout = root.findViewById(R.id.to_rated_movies_refresh_view);
    searchView = root.findViewById(R.id.top_rated_movies_search_view);

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
    moviesViewModel.getAllTopRatedMovies();

    recyclerView = view.findViewById(R.id.top_rated_movies_rv);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpRecyclerView();
    setUpOnClickListener(view);
  }

  private void setUpRecyclerView()
  {
    moviesAdapter = new MoviesAdapter(movies);
    Observer<ArrayList<Movie>> update = moviesAdapter::updateMovieList;
    moviesViewModel.getAllTopRatedMovies()
        .observe(getViewLifecycleOwner(), update);

    setUpSearchView(update);
  }

  private void setUpOnClickListener(View view)
  {
    recyclerView.setAdapter(moviesAdapter);
    /*moviesAdapter.setListener(movie -> {
      PopularMoviesFragmentDirections.ActionNavPopularMoviesToNavSingleMovie action = PopularMoviesFragmentDirections
          .actionNavPopularMoviesToNavSingleMovie();
      action.setMovieIdArg(String.valueOf(movie.getId()));
      Navigation.findNavController(view).navigate(action);
    });*/
  }

  private void refresh()
  {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      setUpRecyclerView();
      swipeRefreshLayout.setRefreshing(false);
    });
  }

  public void setUpSearchView(Observer<ArrayList<Movie>> update)
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
