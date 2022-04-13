package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.home;

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

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Trending;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.media.TrendingAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment
{

  private FragmentHomeBinding binding;
  private HomeViewModel homeViewModel;
  private RecyclerView recyclerView;
  private final ArrayList<Trending> trendingList = new ArrayList<>();
  private TrendingAdapter adapter;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    recyclerView = root.findViewById(R.id.trending_rv);
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
    homeViewModel.getAllTrendingToday();
    recyclerView.hasFixedSize();
    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    setUpRecyclerView();
  }

  private void setUpRecyclerView()
  {
    adapter = new TrendingAdapter(trendingList);
    Observer<ArrayList<Trending>> update = adapter::updateMovieList;
    homeViewModel.getAllTrendingToday()
        .observe(getViewLifecycleOwner(), update);
    recyclerView.setAdapter(adapter);
  }

  /*private void setUpOnClickListener(View view)
  {
    recyclerView.setAdapter(adapter);
    adapter.setListener(trending -> {
      MoviesMainFragmentDirections.ActionNavMainMoviesToNavSingleMovie action = MoviesMainFragmentDirections
          .actionNavMainMoviesToNavSingleMovie();
      action.setMovieIdArg(String.valueOf(trending.getId()));
      Navigation.findNavController(view).navigate(action);
    });
  }*/
}