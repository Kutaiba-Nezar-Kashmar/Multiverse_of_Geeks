package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.CommentAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentSingleTvShowBinding;

public class SingleTvFragment extends Fragment
{
  private FragmentSingleTvShowBinding binding;
  private TextView tvTitle;
  private TextView tvOverview;
  private TextView tvReleaseYear;
  private RatingBar tvRatingBar;
  private ImageView tvPoster;
  private RecyclerView commentRv;
  private TVShowsViewModel tvShowsViewModel;
  private CommentAdapter adapter;
  private int tvId;
  private ArrayList<Comment> comments = new ArrayList<>();

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    tvShowsViewModel = new ViewModelProvider(this).get(TVShowsViewModel.class);

    binding = FragmentSingleTvShowBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    tvTitle = root.findViewById(R.id.single_tv_title);
    tvOverview = root.findViewById(R.id.tv_overview);
    tvReleaseYear = root.findViewById(R.id.tv_release_year);
    tvRatingBar = root.findViewById(R.id.single_tv_rating_bar);
    tvPoster = root.findViewById(R.id.single_tv_image);
    commentRv = root.findViewById(R.id.tv_coming_rv_id);

    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    if (getArguments() != null)
    {
      String id = SingleTvFragmentArgs.fromBundle(getArguments()).getTvShowId();
      tvId = Integer.parseInt(id);
      tvShowsViewModel.findTvShowById(tvId)
          .observe(getViewLifecycleOwner(), tvShow -> {
            tvTitle.setText(tvShow.getName());
            tvOverview.setText(tvShow.getOverview());
            tvReleaseYear.setText(tvShow.getFirst_air_date());
            tvRatingBar.setRating(tvShow.getVote_average());
            Glide.with(view.getContext()).load(
                "https://image.tmdb.org/t/p/w500" + tvShow.getPoster_path())
                .into(tvPoster);
          });
    }

    commentRv.hasFixedSize();
    commentRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpAdapterView();
    commentRv.setAdapter(adapter);
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  private void setUpAdapterView()
  {
    adapter = new CommentAdapter(comments);
    Observer<ArrayList<Comment>> update = adapter::updateCommentList;
    tvShowsViewModel.getAllComments().observe(getViewLifecycleOwner(), update);
  }
}
