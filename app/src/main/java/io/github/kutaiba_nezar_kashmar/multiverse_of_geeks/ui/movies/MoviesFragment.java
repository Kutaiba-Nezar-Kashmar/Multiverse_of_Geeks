package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.movies.MoviesAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentLoginBinding;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentMoviesBinding;

public class MoviesFragment extends Fragment
{
  private FragmentMoviesBinding binding;
  RecyclerView recyclerView;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    MoviesViewModel moviesViewModel = new ViewModelProvider(this)
        .get(MoviesViewModel.class);

    binding = FragmentMoviesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    recyclerView = root.findViewById(R.id.movies_rv);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

    MoviesAdapter moviesAdapter = new MoviesAdapter(moviesViewModel.getMovies());

    recyclerView.setAdapter(moviesAdapter);
    moviesAdapter.setListener(movie -> {
      NavHostFragment.findNavController(this)
          .navigate(R.id.action_nav_movies_to_nav_single_movie);
    });

    return root;
  }

  @Override public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}
