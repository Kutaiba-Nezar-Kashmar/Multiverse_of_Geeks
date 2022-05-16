package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentSingleTvShowBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv.TvComment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv.TvReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaGenreResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaProductionCompaniesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.TvShowCreatorResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.TvShowNetworkResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.adapters.MediaProductionCompanyAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.TvCommentAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.TvCreatorAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.TvNetworkAdapter;

public class SingleTvFragment extends Fragment
{
  private FragmentSingleTvShowBinding binding;
  private TVShowsViewModel tvShowsViewModel;
  private TvCommentAdapter adapter;
  private TvCreatorAdapter tvCreatorAdapter;
  private TvNetworkAdapter networkAdapter;
  private MediaProductionCompanyAdapter productionCompanyAdapter;
  private TvComment comment;
  private final List<TvComment> comments = new ArrayList<>();
  private List<MediaProductionCompaniesResponse> companiesResponses = new ArrayList<>();
  private List<TvShowCreatorResponse> creatorResponses = new ArrayList<>();
  private List<TvShowNetworkResponse> tvShowNetworkResponses = new ArrayList<>();
  private TvReview review;
  private int tvId;
  private TextView tvTitle;
  private TextView tvOverview;
  private TextView tvReleaseYear;
  private TextView tvTagLine;
  private TextView tvEpisodeRunTime;
  private TextView tvGenre;
  private TextView tvEpisodeCount;
  private TextView tvSeasonCount;
  private TextView tvStatus;
  private TextView tvType;
  private TextView tvHomePage;
  private Button toCastButton;
  private Button toSeasonsButton;
  private TextView tvRating;
  private ImageView tvPoster;
  private RecyclerView commentRv;
  private RecyclerView creatorRv;
  private RecyclerView networkRv;
  private RecyclerView productionCompanyRv;
  private Button favButton;
  private RatingBar ratingBar;
  private TextView geekRating;
  private Button commentButton;
  private EditText commentField;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    tvShowsViewModel = new ViewModelProvider(this).get(TVShowsViewModel.class);

    binding = FragmentSingleTvShowBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    checkIfSignedIn();
    favButton = root.findViewById(R.id.tv_fav_button_id);
    tvTitle = root.findViewById(R.id.single_tv_title);
    tvOverview = root.findViewById(R.id.tv_overview);
    tvReleaseYear = root.findViewById(R.id.tv_release_year);
    tvRating = root.findViewById(R.id.single_tv_rating);
    tvPoster = root.findViewById(R.id.single_tv_image);
    commentRv = root.findViewById(R.id.tv_coming_rv_id);
    toCastButton = root.findViewById(R.id.to_tv_cast_button);
    tvTagLine = root.findViewById(R.id.single_tv_tagline);
    tvEpisodeRunTime = root.findViewById(R.id.tv_episode_runtime);
    tvGenre = root.findViewById(R.id.tv_genre);
    tvEpisodeCount = root.findViewById(R.id.tv_episode_count);
    tvSeasonCount = root.findViewById(R.id.tv_season_count);
    tvStatus = root.findViewById(R.id.tv_status);
    tvType = root.findViewById(R.id.tv_type);
    tvHomePage = root.findViewById(R.id.tv_homepage);
    toSeasonsButton = root.findViewById(R.id.to_tv_seasons_button);
    creatorRv = root.findViewById(R.id.tv_creator_rv);
    networkRv = root.findViewById(R.id.tv_network_rv);
    productionCompanyRv = root.findViewById(R.id.tv_production_company_rv);
    ratingBar = root.findViewById(R.id.tv_ratting_bar);
    geekRating = root.findViewById(R.id.geek_tv_rating);
    commentButton = root.findViewById(R.id.tv_post_comment_button);
    commentField = root.findViewById(R.id.comment_edit_text_id_tv);
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    toCastButton.setOnClickListener(view1 -> {
      SingleTvFragmentDirections.ActionNavSingleTvToNavTvCast action = SingleTvFragmentDirections.actionNavSingleTvToNavTvCast();
      action.setTvId(String.valueOf(tvId));
      Navigation.findNavController(view1).navigate(action);
    });

    toSeasonsButton.setOnClickListener(view1 -> {
      SingleTvFragmentDirections.ActionNavSingleTvToNavTvSeasons action = SingleTvFragmentDirections.actionNavSingleTvToNavTvSeasons();
      action.setTvShowId(String.valueOf(tvId));
      Navigation.findNavController(view1).navigate(action);
    });

    if (getArguments() != null)
    {
      String id = SingleTvFragmentArgs.fromBundle(getArguments()).getTvShowId();
      tvId = Integer.parseInt(id);
      tvShowsViewModel.findTvShowById(tvId)
          .observe(getViewLifecycleOwner(), tvShow -> {
            setTvTitle(tvShow);
            setOverview(tvShow);
            setReleaseDate(tvShow);
            tvRating.setText(String.valueOf(tvShow.getVote_average()));
            setTvPoster(view, tvShow);
            setTvTagLine(tvShow);
            tvEpisodeRunTime.setText(
                String.valueOf(tvShow.getEpisode_run_time()));
            setTvGenre(tvShow);
            tvEpisodeCount.setText(
                String.valueOf(tvShow.getNumber_of_episodes()));
            tvSeasonCount.setText(
                String.valueOf(tvShow.getNumber_of_seasons()));
            setTvStatus(tvShow);
            setTvType(tvShow);
            setTvHomePage(tvShow);

            companiesResponses = tvShow.getProduction_companies();
            productionCompanyAdapter.updateCompanyList(companiesResponses);
            creatorResponses = tvShow.getCreated_by();
            tvCreatorAdapter.updateCreatorList(creatorResponses);
            tvShowNetworkResponses = tvShow.getNetworks();
            networkAdapter.updateNetworkList(tvShowNetworkResponses);
            setUpFavoriteTv(tvShow);
          });
      setUpCompanyRv(view);
      setUpCreatorRv(view);
      setUpNetworkRv(view);
    }
    setCommentRv(view);
    setUpRatingBar();
    getGeekAverageRating();
  }

  private void checkIfSignedIn()
  {
    tvShowsViewModel.getCurrentUser()
        .observe(getViewLifecycleOwner(), firebaseUser -> {
          if (firebaseUser != null)
          {
            ratingBar.setVisibility(View.VISIBLE);
            commentField.setVisibility(View.VISIBLE);
            commentButton.setVisibility(View.VISIBLE);
            commentRv.setVisibility(View.VISIBLE);
            commentButton.setOnClickListener(view -> {
              comment = new TvComment(tvId, firebaseUser.getUid(),
                  commentField.getText().toString(),
                  firebaseUser.getDisplayName(),
                  String.valueOf(Calendar.getInstance().getTime()));
              tvShowsViewModel.postComment(comment);
              commentField.getText().clear();
            });
          }
          else
          {
            ratingBar.setVisibility(View.INVISIBLE);
            commentField.setVisibility(View.GONE);
            commentButton.setVisibility(View.GONE);
            commentRv.setVisibility(View.GONE);
          }
        });
  }

  private void setUpRatingBar()
  {
    ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
      review = new TvReview(ratingBar.getRating(), tvId);
      tvShowsViewModel.postReview(review);
    });
  }

  private void getGeekAverageRating()
  {
    List<TvReview> mr = new ArrayList<>();
    tvShowsViewModel.getTvReviews()
        .observe(getViewLifecycleOwner(), tvReviews -> {
          for (TvReview tvReview : tvReviews)
          {
            if (tvReview.getTvId() == tvId)
            {
              mr.add(tvReview);
            }
          }
          String averageValue = String.valueOf(
              tvShowsViewModel.calculateAverage(mr));
          geekRating.setText(averageValue);
        });
  }

  private void setCommentRv(@NonNull View view)
  {
    commentRv.hasFixedSize();
    commentRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpAdapterView();
    commentRv.setAdapter(adapter);
  }

  private void setTvHomePage(SingleTvShowResponse tvShow)
  {
    if (tvShow.getHomepage() == null)
    {
      tvHomePage.setText(R.string.not_found);
      tvHomePage.setVisibility(View.GONE);
    }
    tvHomePage.setText(tvShow.getHomepage());
  }

  private void setTvType(SingleTvShowResponse tvShow)
  {
    if (tvShow.getType() == null)
    {
      tvType.setText(R.string.not_found);
      tvType.setVisibility(View.GONE);
    }
    tvType.setText(tvShow.getType());
  }

  private void setTvStatus(SingleTvShowResponse tvShow)
  {
    if (tvShow.getStatus() == null)
    {
      tvStatus.setText(R.string.not_found);
      tvStatus.setVisibility(View.GONE);
    }
    tvStatus.setText(tvShow.getStatus());
  }

  private void setTvGenre(SingleTvShowResponse tvShow)
  {
    tvGenre.setText("");
    if (tvShow.getGenres() == null)
    {
      tvGenre.setVisibility(View.GONE);
    }
    for (MediaGenreResponse genreItem : tvShow.getGenres())
    {
      tvGenre.append(genreItem.getName() + "\n");
    }
  }

  private void setTvTagLine(SingleTvShowResponse tvShow)
  {
    if (tvShow.getTagline() == null)
    {
      tvTagLine.setText(R.string.not_found);
      tvTagLine.setVisibility(View.GONE);
    }
    tvTagLine.setText(tvShow.getTagline());
    ;
  }

  private void setTvPoster(@NonNull View view, SingleTvShowResponse tvShow)
  {
    if (tvShow.getPoster_path() == null)
    {
      tvPoster.setVisibility(View.GONE);
    }
    Glide.with(view.getContext())
        .load("https://image.tmdb.org/t/p/w500" + tvShow.getPoster_path())
        .into(tvPoster);
  }

  private void setReleaseDate(SingleTvShowResponse tvShow)
  {
    if (tvShow.getFirst_air_date() == null)
    {
      tvReleaseYear.setText(R.string.not_found);
      tvReleaseYear.setVisibility(View.GONE);
    }
    tvReleaseYear.setText(tvShow.getFirst_air_date());
  }

  private void setOverview(SingleTvShowResponse tvShow)
  {
    if (tvShow.getOverview() == null)
    {
      tvOverview.setText(R.string.not_found);
      tvOverview.setVisibility(View.GONE);
    }
    tvOverview.setText(tvShow.getOverview());
  }

  private void setTvTitle(SingleTvShowResponse tvShow)
  {
    if (tvShow.getName() == null)
    {
      tvTitle.setText(R.string.not_found);
      tvTitle.setVisibility(View.GONE);
    }
    tvTitle.setText(tvShow.getName());
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  private void setUpAdapterView()
  {
    adapter = new TvCommentAdapter(comments);
    Observer<List<TvComment>> update = adapter::updateTvCommentList;
    tvShowsViewModel.getTvComments(tvId)
        .observe(getViewLifecycleOwner(), tvComments -> {
          for (TvComment tc : tvComments)
          {
            tc.setUserImage(tvShowsViewModel.getProfileImage(tc.getUserId()));
          }
          update.onChanged(tvComments);
        });
  }

  private void setUpCompanyRv(View view)
  {
    productionCompanyRv.hasFixedSize();
    productionCompanyRv.setLayoutManager(
        new LinearLayoutManager(view.getContext(),
            LinearLayoutManager.HORIZONTAL, false));
    productionCompanyAdapter = new MediaProductionCompanyAdapter(
        companiesResponses);
    productionCompanyRv.setAdapter(productionCompanyAdapter);
  }

  private void setUpCreatorRv(View view)
  {
    creatorRv.hasFixedSize();
    creatorRv.setLayoutManager(new LinearLayoutManager(view.getContext(),
        LinearLayoutManager.HORIZONTAL, false));
    tvCreatorAdapter = new TvCreatorAdapter(creatorResponses);
    creatorRv.setAdapter(tvCreatorAdapter);
  }

  private void setUpNetworkRv(View view)
  {
    networkRv.hasFixedSize();
    networkRv.setLayoutManager(new LinearLayoutManager(view.getContext(),
        LinearLayoutManager.HORIZONTAL, false));
    networkAdapter = new TvNetworkAdapter(tvShowNetworkResponses);
    networkRv.setAdapter(networkAdapter);
  }

  private void setUpFavoriteTv(SingleTvShowResponse tv)
  {

    tvShowsViewModel.getSingleFavoriteTvShow(tvId)
        .observe(getViewLifecycleOwner(), singleTvShowResponse -> {
          if (singleTvShowResponse != null)
          {
            favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
            favButton.setOnClickListener(
                view -> tvShowsViewModel.deleteFavoriteTvShow(tv));
          }
          else
          {
            favButton.setBackgroundResource(R.drawable.fav_border_ic);
            favButton.setOnClickListener(
                view -> tvShowsViewModel.insertFavoriteTvShow(tv));
          }
        });
  }
}
