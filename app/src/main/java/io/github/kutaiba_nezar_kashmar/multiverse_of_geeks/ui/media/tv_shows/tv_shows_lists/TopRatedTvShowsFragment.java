package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.tv_shows_lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentTopRatedTvShowsBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.TvShow;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.TVShowsViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.TvShowsMainFragmentDirections;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.TVShowAdapter;

public class TopRatedTvShowsFragment extends Fragment
{
  private FragmentTopRatedTvShowsBinding binding;
  private TVShowsViewModel tvShowsViewModel;
  private TVShowAdapter adapter;
  private final List<TvShow> tvShows = new ArrayList<>();
  private int pageNum = 1;
  private RecyclerView recyclerView;
  private SwipeRefreshLayout swipeRefreshLayout;
  private Button leftArrow;
  private Button rightArrow;
  private TextView pageNumber;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    tvShowsViewModel = new ViewModelProvider(this).get(TVShowsViewModel.class);
    binding = FragmentTopRatedTvShowsBinding.inflate(inflater, container,
        false);
    View root = binding.getRoot();

    //Views
    swipeRefreshLayout = root.findViewById(R.id.top_rated_tv_refresh_view);
    leftArrow = root.findViewById(R.id.top_rated_tv_left_arrow);
    rightArrow = root.findViewById(R.id.top_rated_tv_right_arrow);
    pageNumber = root.findViewById(R.id.top_rated_tv_page_number);

    refresh();
    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    //Setup recyclerview
    tvShowsViewModel.getAllTopRatedTvShows(pageNum);
    recyclerView = view.findViewById(R.id.top_rated_tv_rv);
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    setUpRecyclerView();
    setUpOnClickListener(view);
    setUpPageChange();
  }

  private void setUpRecyclerView()
  {
    adapter = new TVShowAdapter(tvShows);

    //Setup observer for a list of TvShow object
    Observer<List<TvShow>> update = adapter::updateTVShowList;
    tvShowsViewModel.getAllTopRatedTvShows(pageNum)
        .observe(getViewLifecycleOwner(), update);
  }

  private void setUpOnClickListener(View view)
  {
    recyclerView.setAdapter(adapter);
    adapter.setListener(tvShow -> {
      TvShowsMainFragmentDirections.ActionNavTvToNavSingleTv action = TvShowsMainFragmentDirections.actionNavTvToNavSingleTv();
      action.setTvShowId(String.valueOf(tvShow.getId()));
      Navigation.findNavController(view).navigate(action);
    });
  }

  private void refresh()
  {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      setUpRecyclerView();
      swipeRefreshLayout.setRefreshing(false);
    });
  }

  private void setUpPageChange()
  {
    rightArrow.setOnClickListener(view -> {
      incrementPage();
      setUpRecyclerView();
    });
    leftArrow.setOnClickListener(view -> {
      decrementPage();
      setUpRecyclerView();
    });
  }

  private void display()
  {
    pageNumber.setText(String.valueOf(pageNum));
  }

  private void incrementPage()
  {
    if (pageNum < 10)
    {
      pageNum = pageNum + 1;
      display();
    }
  }

  private void decrementPage()
  {
    if (pageNum > 1)
      pageNum = pageNum - 1;
    display();
  }
}
