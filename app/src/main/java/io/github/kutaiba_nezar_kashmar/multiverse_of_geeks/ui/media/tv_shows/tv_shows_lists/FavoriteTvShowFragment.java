package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.tv_shows_lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentFavoriteTvShowBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.TVShowsViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.FavoriteTvShowAdapter;

public class FavoriteTvShowFragment extends Fragment
{
  private FragmentFavoriteTvShowBinding binding;
  private TVShowsViewModel viewModel;
  private final List<SingleTvShowResponse> tvShows = new ArrayList<>();
  private RecyclerView recyclerView;
  private SwipeRefreshLayout refreshLayout;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    viewModel = new ViewModelProvider(this).get(TVShowsViewModel.class);
    binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    //Views
    refreshLayout = root.findViewById(R.id.tv_fav_refresh_view);
    recyclerView = root.findViewById(R.id.favorite_tv_rv);

    refresh();
    return root;
  }

  @Override
  public void onDestroy()
  {
    super.onDestroy();
    binding = null;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    //Setup recyclerview
    viewModel.getFavoriteTvShows();
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    setUpRecyclerView();
  }

  private void setUpRecyclerView()
  {
    FavoriteTvShowAdapter adapter = new FavoriteTvShowAdapter(tvShows);

    //Setup observer for a list of SingleTvShowResponse
    Observer<List<SingleTvShowResponse>> update = adapter::updateTvShowList;
    viewModel.getFavoriteTvShows().observe(getViewLifecycleOwner(), update);
    recyclerView.setAdapter(adapter);
  }

  private void refresh()
  {
    refreshLayout.setOnRefreshListener(() -> {
      setUpRecyclerView();
      refreshLayout.setRefreshing(false);
    });
  }
}
