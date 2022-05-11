package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.movies_lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentTopRatedMoviesBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.MoviesMainFragmentDirections;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.MoviesViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters.MoviesAdapter;

public class TopRatedMoviesFragment extends Fragment
{
  private FragmentTopRatedMoviesBinding binding;
  private RecyclerView recyclerView;
  private final List<Movie> movies = new ArrayList<>();
  private MoviesViewModel moviesViewModel;
  private MoviesAdapter moviesAdapter;
  private SwipeRefreshLayout swipeRefreshLayout;
  private Button leftArrow;
  private Button rightArrow;
  private TextView pageNumber;
  private int pageNum = 1;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
    binding = FragmentTopRatedMoviesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    swipeRefreshLayout = root.findViewById(R.id.to_rated_movies_refresh_view);
    leftArrow = root.findViewById(R.id.top_rated_movie_left_arrow);
    rightArrow = root.findViewById(R.id.top_rated_movie_right_arrow);
    pageNumber = root.findViewById(R.id.top_rated_movie_page_number);
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
    moviesViewModel.getAllTopRatedMovies(pageNum);

    recyclerView = view.findViewById(R.id.top_rated_movies_rv);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpRecyclerView();
    setUpOnClickListener(view);
    setUpPageChange();
  }

  private void setUpRecyclerView()
  {
    moviesAdapter = new MoviesAdapter(movies);
    Observer<List<Movie>> update = moviesAdapter::updateMovieList;
    moviesViewModel.getAllTopRatedMovies(pageNum)
        .observe(getViewLifecycleOwner(), update);
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

  private void refresh()
  {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      setUpRecyclerView();
      swipeRefreshLayout.setRefreshing(false);
    });
  }

  private void setUpPageChange()
  {
    rightArrow.setOnClickListener(view -> {
      incrementPage();
      setUpRecyclerView();
    });
    leftArrow.setOnClickListener(view -> {
      decrementPage();
      setUpRecyclerView();
    });
  }

  private void display()
  {
    pageNumber.setText(String.valueOf(pageNum));
  }

  private void incrementPage()
  {
    if (pageNum < 10)
    {
      pageNum = pageNum + 1;
      display();
    }
  }

  private void decrementPage()
  {
    if (pageNum > 1)
      pageNum = pageNum - 1;
    display();
  }
}
