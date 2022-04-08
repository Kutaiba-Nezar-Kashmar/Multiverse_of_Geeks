package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.MoviesAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentMoviesBinding;

public class MoviesFragment extends Fragment
{
  private FragmentMoviesBinding binding;
  private RecyclerView recyclerView;
  private final ArrayList<Movie> movies = new ArrayList<>();
  private MoviesViewModel moviesViewModel;
  private MoviesAdapter moviesAdapter;
  private SwipeRefreshLayout swipeRefreshLayout;
  private Button popularButton;
  private Button topButton;
  private Button nowButton;
  private Button upButton;
  private SearchView searchView;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

    binding = FragmentMoviesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    swipeRefreshLayout = root.findViewById(R.id.movies_refresh_view);
    popularButton = root.findViewById(R.id.popular_movies_button);
    topButton = root.findViewById(R.id.top_movies_button);
    nowButton = root.findViewById(R.id.now_movies_button);
    upButton = root.findViewById(R.id.up_movies_button);
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
    moviesViewModel.getAllPopularMovies();

    recyclerView = view.findViewById(R.id.movies_rv);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpRecyclerView();
    setUpOnClickListener(view);
  }

  private void setUpRecyclerView()
  {
    moviesAdapter = new MoviesAdapter(movies);
    Observer<ArrayList<Movie>> update = moviesAdapter::updateMovieList;
    moviesViewModel.getAllPopularMovies()
        .observe(getViewLifecycleOwner(), update);

    popularButton.setOnClickListener(
        view -> moviesViewModel.getAllPopularMovies()
            .observe(getViewLifecycleOwner(), update));
    topButton.setOnClickListener(view -> moviesViewModel.getAllTopRatedMovies()
        .observe(getViewLifecycleOwner(), update));
    nowButton.setOnClickListener(
        view -> moviesViewModel.getAllNowPlayingMovies()
            .observe(getViewLifecycleOwner(), update));
    upButton.setOnClickListener(view -> moviesViewModel.getAllUpcomingMovies()
        .observe(getViewLifecycleOwner(), update));

    setUpSearchView(update);
  }

  private void setUpOnClickListener(View view)
  {
    recyclerView.setAdapter(moviesAdapter);
    moviesAdapter.setListener(movie -> {
      MoviesFragmentDirections.ActionNavMoviesToNavSingleMovie action = MoviesFragmentDirections
          .actionNavMoviesToNavSingleMovie();
      action.setMovieIdArg(String.valueOf(movie.getId()));
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
