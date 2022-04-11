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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies.MoviesViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.movies.MoviesAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentSimilarMoviesBinding;

public class SimilarMoviesFragment extends Fragment
{
  private FragmentSimilarMoviesBinding binding;
  private MoviesViewModel moviesViewModel;
  private final ArrayList<Movie> movies = new ArrayList<>();
  private RecyclerView recyclerView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

    binding = FragmentSimilarMoviesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
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
      String id = SimilarMoviesFragmentArgs.fromBundle(getArguments())
          .getMovieId();
      int movieId = Integer.parseInt(id);
      moviesViewModel.getAllSimilarMovies(movieId);
      recyclerView.hasFixedSize();
      recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
      MoviesAdapter adapter = new MoviesAdapter(movies);
      Observer<ArrayList<Movie>> update = adapter::updateMovieList;
      moviesViewModel.getAllSimilarMovies(movieId)
          .observe(getViewLifecycleOwner(), update);
      recyclerView.setAdapter(adapter);
      adapter.setListener(movie -> {
        SimilarMoviesFragmentDirections.ActionNavSimilarMoviesToNavSingleMovie action = SimilarMoviesFragmentDirections
            .actionNavSimilarMoviesToNavSingleMovie();
        action.setMovieIdArg(String.valueOf(movieId));
        Navigation.findNavController(view).navigate(action);
      });
    }
  }
}
