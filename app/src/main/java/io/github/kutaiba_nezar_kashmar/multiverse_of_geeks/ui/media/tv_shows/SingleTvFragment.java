package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaGenreResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaProductionCompaniesResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.TvShowCreatorResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.TvShowNetworkResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.adapters.MediaProductionCompanyAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.TvCreatorAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.TvNetworkAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.TvShowReviewsAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentSingleTvShowBinding;

public class SingleTvFragment extends Fragment
{
  private FragmentSingleTvShowBinding binding;
  private TVShowsViewModel tvShowsViewModel;
  private TvShowReviewsAdapter adapter;
  private TvCreatorAdapter tvCreatorAdapter;
  private TvNetworkAdapter networkAdapter;
  private MediaProductionCompanyAdapter productionCompanyAdapter;
  private final ArrayList<Comment> comments = new ArrayList<>();
  private ArrayList<MediaProductionCompaniesResponse> companiesResponses = new ArrayList<>();
  private ArrayList<TvShowCreatorResponse> creatorResponses = new ArrayList<>();
  private ArrayList<TvShowNetworkResponse> tvShowNetworkResponses = new ArrayList<>();
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
  private TextView tvCreator;
  private TextView tvNetwork;
  private TextView tvProductionCompany;
  private Button toCastButton;
  private Button toSeasonsButton;
  private TextView tvRating;
  private ImageView tvPoster;
  private RecyclerView commentRv;
  private RecyclerView creatorRv;
  private RecyclerView networkRv;
  private RecyclerView productionCompanyRv;
  private Button favButton;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    tvShowsViewModel = new ViewModelProvider(this).get(TVShowsViewModel.class);

    binding = FragmentSingleTvShowBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
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
    tvCreator = root.findViewById(R.id.tv_creator_heading);
    tvNetwork = root.findViewById(R.id.tv_network_heading);
    tvProductionCompany = root.findViewById(R.id.tv_production_company_heading);
    toSeasonsButton = root.findViewById(R.id.to_tv_seasons_button);
    creatorRv = root.findViewById(R.id.tv_creator_rv);
    networkRv = root.findViewById(R.id.tv_network_rv);
    productionCompanyRv = root.findViewById(R.id.tv_production_company_rv);
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    toCastButton.setOnClickListener(view1 -> {
      SingleTvFragmentDirections.ActionNavSingleTvToNavTvCast action = SingleTvFragmentDirections
          .actionNavSingleTvToNavTvCast();
      action.setTvId(String.valueOf(tvId));
      Navigation.findNavController(view1).navigate(action);
    });

    toSeasonsButton.setOnClickListener(view1 -> {
      SingleTvFragmentDirections.ActionNavSingleTvToNavTvSeasons action = SingleTvFragmentDirections
          .actionNavSingleTvToNavTvSeasons();
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
            tvEpisodeRunTime
                .setText(String.valueOf(tvShow.getEpisode_run_time()));
            setTvGenre(tvShow);
            tvEpisodeCount
                .setText(String.valueOf(tvShow.getNumber_of_episodes()));
            tvSeasonCount
                .setText(String.valueOf(tvShow.getNumber_of_seasons()));
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
    adapter = new TvShowReviewsAdapter(comments);
    Observer<ArrayList<Comment>> update = adapter::updateCommentList;
    tvShowsViewModel.getAllComments(tvId)
        .observe(getViewLifecycleOwner(), update);
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
            favButton.setOnClickListener(view -> {
              tvShowsViewModel.deleteFavoriteTvShow(tv);
            });
          }
          else
          {
            favButton.setBackgroundResource(R.drawable.fav_border_ic);
            favButton.setOnClickListener(view -> {
              tvShowsViewModel.insertFavoriteTvShow(tv);
            });
          }
        });
  }
}
