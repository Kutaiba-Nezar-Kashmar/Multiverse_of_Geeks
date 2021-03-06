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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentSimilarMoviesBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.MoviesViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters.MoviesAdapter;

public class SimilarMoviesFragment extends Fragment
{
  private FragmentSimilarMoviesBinding binding;
  private MoviesViewModel moviesViewModel;
  private final List<Movie> movies = new ArrayList<>();
  private RecyclerView recyclerView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
    binding = FragmentSimilarMoviesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    //Views
    recyclerView = root.findViewById(R.id.similar_movie_rv);

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
    if (getArguments() != null)
    {
      //Get a movieId from Navigation component arguments
      String id = SimilarMoviesFragmentArgs.fromBundle(getArguments())
          .getMovieId();
      int movieId = Integer.parseInt(id);

      //Setup recyclerview
      moviesViewModel.getAllSimilarMovies(movieId);
      recyclerView.hasFixedSize();
      recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
      MoviesAdapter adapter = new MoviesAdapter(movies);

      //Setup observer for a list of Movie objects
      Observer<List<Movie>> update = adapter::updateMovieList;
      moviesViewModel.getAllSimilarMovies(movieId)
          .observe(getViewLifecycleOwner(), update);
      recyclerView.setAdapter(adapter);

      //Setup listener for recyclerview item
      adapter.setListener(movie -> {
        SimilarMoviesFragmentDirections.ActionNavSimilarMoviesToNavSingleMovie action = SimilarMoviesFragmentDirections.actionNavSimilarMoviesToNavSingleMovie();
        action.setMovieIdArg(String.valueOf(movieId));
        Navigation.findNavController(view).navigate(action);
      });
    }
  }
}
