package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentSingleMovieBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieComment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaGenreResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaProductionCompaniesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.adapters.MediaProductionCompanyAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters.MovieCommentAdapter;

public class SingleMovieFragment extends Fragment
{
  private FragmentSingleMovieBinding binding;
  private MoviesViewModel moviesViewModel;
  private MovieCommentAdapter commentAdapter;
  private FirebaseRecyclerOptions<MovieComment> options;
  private MediaProductionCompanyAdapter productionCompanyAdapter;
  private MovieReview review;
  private MovieComment comment;
  private List<MediaProductionCompaniesResponse> companiesResponses = new ArrayList<>();
  private List<MovieComment> comments = new ArrayList<>();
  private int movieId;
  private RecyclerView commentsRecyclerView;
  private RecyclerView companyRv;
  private TextView movieTitle;
  private TextView movieTagline;
  private TextView budget;
  private TextView revenue;
  private TextView genre;
  private TextView homePage;
  private TextView collectionName;
  private TextView collectionHeader;
  private TextView movieOverview;
  private TextView movieReleaseYear;
  private TextView movieRatting;
  private TextView geekRating;
  private ImageView collectionPoster;
  private ImageView moviePoster;
  private Button favButton;
  private Button toSimilarButton;
  private Button toCastButton;
  private RatingBar ratingBar;
  private Button commentButton;
  private EditText commentField;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

    binding = FragmentSingleMovieBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    checkIfSignedIn();
    favButton = root.findViewById(R.id.movie_fav_button_id);
    movieTitle = root.findViewById(R.id.single_movie_title);
    movieOverview = root.findViewById(R.id.movie_overview);
    movieReleaseYear = root.findViewById(R.id.movie_release_year);
    movieRatting = root.findViewById(R.id.single_movie_rating);
    moviePoster = root.findViewById(R.id.single_movie_image);
    commentsRecyclerView = root.findViewById(R.id.movie_coming_rv_id);
    toCastButton = root.findViewById(R.id.to_movie_cast_button);
    movieTagline = root.findViewById(R.id.single_movie_tagline);
    budget = root.findViewById(R.id.movie_budget);
    revenue = root.findViewById(R.id.movie_revenue);
    genre = root.findViewById(R.id.movie_genre);
    homePage = root.findViewById(R.id.movie_home_page);
    collectionName = root.findViewById(R.id.movie_collection_name);
    collectionHeader = root.findViewById(R.id.movie_collection_header);
    collectionPoster = root.findViewById(R.id.movie_collection_poster);
    companyRv = root.findViewById(R.id.movie_production_companies_rv);
    ratingBar = root.findViewById(R.id.movie_rating_bar);
    geekRating = root.findViewById(R.id.single_movie_rating_geek);
    toSimilarButton = root.findViewById(R.id.to_similar_movie_button);
    commentButton = root.findViewById(R.id.post_comment_button);
    commentField = root.findViewById(R.id.movie_comment_field);
    setUpToCastButton();
    setUpSimilarButton();
    setUpRatingBar();
    getGeekAverageRating();
    setUpCommentButton();
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
            movieRatting.setText(String.valueOf(movie.getVote_average()));
            Glide.with(view.getContext()).load(
                "https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                .into(moviePoster);
            movieTagline.setText(movie.getTagline());
            budget.setText(String.valueOf(movie.getBudget()));
            revenue.setText(String.valueOf(movie.getRevenue()));
            if (movie.getGenres() != null)
            {
              genre.setText("");
              for (MediaGenreResponse genreItem : movie.getGenres())
              {
                genre.append(genreItem.getName() + "\n");
              }
            }
            homePage.setText(movie.getHomepage());
            if (movie.getBelongs_to_collection() != null)
            {
              collectionHeader.setVisibility(View.VISIBLE);
              collectionName.setVisibility(View.VISIBLE);
              collectionPoster.setVisibility(View.VISIBLE);
              collectionName.setText("");
              collectionName
                  .setText(movie.getBelongs_to_collection().getName());
              Glide.with(view.getContext()).load(
                  "https://image.tmdb.org/t/p/w500" + movie
                      .getBelongs_to_collection().getPoster_path())
                  .into(collectionPoster);
            }
            else
            {
              collectionPoster.setImageResource(android.R.color.transparent);
              collectionHeader.setVisibility(View.GONE);
              collectionName.setVisibility(View.GONE);
              collectionPoster.setVisibility(View.GONE);
            }

            companiesResponses = movie.getProduction_companies();
            productionCompanyAdapter.updateCompanyList(companiesResponses);
            setUpFavorite(movie);
          });
      setUpCompanyRv(view);
      setUpCommentRV(view);
    }
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  private void setUpToCastButton()
  {
    toCastButton.setOnClickListener(view -> {
      SingleMovieFragmentDirections.ActionNavSingleMovieToNavMovieCast action = SingleMovieFragmentDirections
          .actionNavSingleMovieToNavMovieCast();
      action.setMovieId(String.valueOf(movieId));
      Navigation.findNavController(view).navigate(action);
    });
  }

  private void setUpSimilarButton()
  {
    toSimilarButton.setOnClickListener(view -> {
      SingleMovieFragmentDirections.ActionNavSingleMovieToNavSimilarMovies action = SingleMovieFragmentDirections
          .actionNavSingleMovieToNavSimilarMovies()
          .setMovieId(String.valueOf(movieId));
      Navigation.findNavController(view).navigate(action);
    });
  }

  private void setUpRatingBar()
  {
    ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
      review = new MovieReview(ratingBar.getRating(), movieId);
      moviesViewModel.postReview(review);
    });
  }

  private void getGeekAverageRating()
  {
    List<MovieReview> mr = new ArrayList<>();
    moviesViewModel.getMovieReviews()
        .observe(getViewLifecycleOwner(), movieReviews -> {
          for (MovieReview movieReview : movieReviews)
          {
            if (movieReview.getMovieId() == movieId)
            {
              mr.add(movieReview);
            }
          }
          String averageValue = String
              .valueOf(moviesViewModel.calculateAverage(mr));
          geekRating.setText(averageValue);
        });
  }

  private void setUpCommentButton()
  {
    commentButton.setOnClickListener(view -> {
      comment = new MovieComment(movieId, commentField.getText().toString());
      moviesViewModel.postComment(comment);
      commentField.getText().clear();
    });
  }

  private void checkIfSignedIn()
  {
    moviesViewModel.getCurrentUser()
        .observe(getViewLifecycleOwner(), firebaseUser -> {
          if (firebaseUser != null)
          {
            ratingBar.setVisibility(View.VISIBLE);
            commentField.setVisibility(View.VISIBLE);
            commentButton.setVisibility(View.VISIBLE);
          }
          else
          {
            ratingBar.setVisibility(View.INVISIBLE);
            commentField.setVisibility(View.GONE);
            commentButton.setVisibility(View.GONE);
          }
        });
  }

  private void setUpCommentRV(View view)
  {
    commentAdapter = new MovieCommentAdapter(comments);
    Observer<List<MovieComment>> update = commentAdapter::updateMovieCommentList;
    moviesViewModel.getMovieComments(movieId)
        .observe(getViewLifecycleOwner(), update);
    commentsRecyclerView.hasFixedSize();
    commentsRecyclerView.setLayoutManager(
        new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL,
            false));
    commentsRecyclerView.setAdapter(commentAdapter);
  }

  private void setUpCompanyRv(View view)
  {
    companyRv.hasFixedSize();
    companyRv.setLayoutManager(new LinearLayoutManager(view.getContext(),
        LinearLayoutManager.HORIZONTAL, false));
    productionCompanyAdapter = new MediaProductionCompanyAdapter(
        companiesResponses);
    companyRv.setAdapter(productionCompanyAdapter);
  }

  private void setUpFavorite(SingleMovieResponse singleMovie)
  {
    moviesViewModel.getSingleFavoriteMovie(movieId)
        .observe(getViewLifecycleOwner(), singleMovieResponse -> {
          if (singleMovieResponse != null)
          {
            favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
            favButton.setOnClickListener(view -> {
              moviesViewModel.deleteMovie(singleMovie);
            });
          }
          else
          {
            favButton.setBackgroundResource(R.drawable.fav_border_ic);
            favButton.setOnClickListener(view -> {
              moviesViewModel.insertMovie(singleMovie);
            });
          }
        });
  }
}
