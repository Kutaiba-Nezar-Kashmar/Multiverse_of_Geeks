package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.cast.tv_show_cast;

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
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentTvShowCastBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Cast;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.cast.CastViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.TvCastAdapter;

public class TvShowCastFragment extends Fragment
{
  private FragmentTvShowCastBinding binding;
  private CastViewModel castViewModel;
  private final List<Cast> casts = new ArrayList<>();
  private RecyclerView recyclerView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    castViewModel = new ViewModelProvider(this).get(CastViewModel.class);

    binding = FragmentTvShowCastBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    //Views
    recyclerView = root.findViewById(R.id.tv_cast_rv);

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
    if (getArguments() != null)
    {
      //receive movieId from NavigationComponent argument

      String id = TvShowCastFragmentArgs.fromBundle(getArguments()).getTvId();
      int tvId = Integer.parseInt(id);

      //Setup recyclerView
      castViewModel.getTvShowCast(tvId);
      recyclerView.hasFixedSize();
      recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
      TvCastAdapter adapter = new TvCastAdapter(casts);

      //Setup observer for a List of Cast
      Observer<List<Cast>> update = adapter::updateTvCastList;
      castViewModel.getTvShowCast(tvId)
          .observe(getViewLifecycleOwner(), update);
      recyclerView.setAdapter(adapter);
    }
  }
}
