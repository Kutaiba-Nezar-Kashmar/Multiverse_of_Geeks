package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
  CardView cardView;
  RecyclerView recyclerView;


  public MovieGenreFragment()
  {
  }

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    MoviesViewModel moviesViewModel = new ViewModelProvider(this)
        .get(MoviesViewModel.class);

    binding = FragmentMovieGenreBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    /**
     * this line is written because when trying to use:
     * cardView = (CardView) root.findViewById(R.id.genre_card_view);
     * a null pointer is thrown
     * */
    LinearLayout linearLayout = (LinearLayout) inflater
        .inflate(R.layout.movie_genre_item, null);

    recyclerView = (RecyclerView) root.findViewById(R.id.genre_rv);
    cardView = (CardView) linearLayout.findViewById(R.id.genre_card_view);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

    recyclerView.setAdapter(moviesViewModel.getAdapter());

    moviesViewModel.getAdapter().setListener(movieGenre -> {
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
