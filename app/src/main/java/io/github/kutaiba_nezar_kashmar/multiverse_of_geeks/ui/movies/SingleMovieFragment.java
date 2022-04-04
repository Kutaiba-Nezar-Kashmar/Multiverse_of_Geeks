package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentSingleMovieBinding;

public class SingleMovieFragment extends Fragment
{
  private FragmentSingleMovieBinding binding;
  private MoviesViewModel moviesViewModel;
  private TextView movieTitle;
  private TextView movieOverview;
  private TextView movieReleaseYear;
  private RatingBar movieRatting;
  private ImageView moviePoster;
  private int movieId;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

    binding = FragmentSingleMovieBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    movieTitle = root.findViewById(R.id.single_movie_title);
    movieOverview = root.findViewById(R.id.movie_overview);
    movieReleaseYear = root.findViewById(R.id.movie_release_year);
    movieRatting = root.findViewById(R.id.single_movie_rating_bar);
    moviePoster = root.findViewById(R.id.single_movie_image);

    return root;
  }

  @Override public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    if (getArguments() != null)
    {
      String id = SingleMovieFragmentArgs.fromBundle(getArguments())
          .getMovieIdArg();
      movieId = Integer.parseInt(id);
      moviesViewModel.findMovieById(movieId)
          .observe(getViewLifecycleOwner(), movie -> {
            movieTitle.setText(movie.getTitle());
            movieOverview.setText(movie.getOverview());
            movieReleaseYear.setText(movie.getRelease_date());
            movieRatting.setRating(movie.getVote_average());
            Glide.with(view.getContext()).load(
                "https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                .into(moviePoster);
          });
    }
  }

  @Override public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

}
