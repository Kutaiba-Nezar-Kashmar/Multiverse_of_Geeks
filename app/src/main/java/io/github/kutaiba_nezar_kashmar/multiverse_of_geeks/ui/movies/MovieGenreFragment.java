package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.MovieGenre;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.movies.MovieGenreAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentMovieGenreBinding;

public class MovieGenreFragment extends Fragment
{
  private FragmentMovieGenreBinding binding;
  private MovieGenreAdapter adapter;
  RecyclerView recyclerView;


  public MovieGenreFragment()
  {

  }

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {

    binding = FragmentMovieGenreBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    MoviesViewModel moviesViewModel = new ViewModelProvider(this)
        .get(MoviesViewModel.class);

    recyclerView = root.findViewById(R.id.genre_rv);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

    moviesViewModel.setUpGenreList();
    adapter = new MovieGenreAdapter(moviesViewModel.retrievedGenreList());
    recyclerView.setAdapter(adapter);

    adapter.setListener(movieGenre -> {
      Toast.makeText(getContext(), "dsfsdf", Toast.LENGTH_SHORT).show();
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_nav_movies_genre_to_nav_movies);

    });
    return root;
  }
  @Override public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}
