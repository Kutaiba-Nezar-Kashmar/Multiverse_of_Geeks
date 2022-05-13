package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentTvShowSeasonsBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.TvShowSeasonResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.SeasonAdapter;

public class TvShowSeasonsFragment extends Fragment
{
  private FragmentTvShowSeasonsBinding binding;
  private TVShowsViewModel viewModel;
  private SeasonAdapter adapter;
  private List<TvShowSeasonResponse> seasons = new ArrayList<>();
  private RecyclerView seasonRv;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    viewModel = new ViewModelProvider(this).get(TVShowsViewModel.class);
    binding = FragmentTvShowSeasonsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    seasonRv = root.findViewById(R.id.seasons_rv);
    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    if (getArguments() != null)
    {
      String id = TvShowSeasonsFragmentArgs.fromBundle(getArguments())
          .getTvShowId();
      int tvId = Integer.parseInt(id);
      viewModel.findTvShowById(tvId)
          .observe(getViewLifecycleOwner(), tvShow -> {
            seasons = tvShow.getSeasons();
            adapter.updateSeasonsList(seasons);
          });
      setSeasonRv(view);
    }
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  private void setSeasonRv(View view)
  {
    seasonRv.hasFixedSize();
    seasonRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
    adapter = new SeasonAdapter(seasons);
    seasonRv.setAdapter(adapter);
  }
}
