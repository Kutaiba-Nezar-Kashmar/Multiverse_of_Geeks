package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.movies.MovieReviewsAdapter;
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
  private final ArrayList<Comment> comments = new ArrayList<>();
  private RecyclerView commentsRecyclerView;
  private MovieReviewsAdapter movieReviewsAdapter;

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
    commentsRecyclerView = root.findViewById(R.id.movie_coming_rv_id);
    Button toCastButton = root.findViewById(R.id.to_movie_cast_button);
    toCastButton.setOnClickListener(view -> {
      SingleMovieFragmentDirections.ActionNavSingleMovieToNavMovieCast action = SingleMovieFragmentDirections
          .actionNavSingleMovieToNavMovieCast();
      action.setMovieId(String.valueOf(movieId));
      Navigation.findNavController(view).navigate(action);
    });

    Button toSimilarButton = root.findViewById(R.id.to_similar_movie_button);
    toSimilarButton.setOnClickListener(view -> {
      SingleMovieFragmentDirections.ActionNavSingleMovieToNavSimilarMovies action = SingleMovieFragmentDirections
          .actionNavSingleMovieToNavSimilarMovies()
          .setMovieId(String.valueOf(movieId));
      Navigation.findNavController(view).navigate(action);
    });

    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view,
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

    commentsRecyclerView.hasFixedSize();
    commentsRecyclerView
        .setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpAdapterView();
    commentsRecyclerView.setAdapter(movieReviewsAdapter);
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  private void setUpAdapterView()
  {
    movieReviewsAdapter = new MovieReviewsAdapter(comments);
    Observer<ArrayList<Comment>> update = movieReviewsAdapter::updateCommentList;
    moviesViewModel.getAllComments(movieId)
        .observe(getViewLifecycleOwner(), update);
  }

}
