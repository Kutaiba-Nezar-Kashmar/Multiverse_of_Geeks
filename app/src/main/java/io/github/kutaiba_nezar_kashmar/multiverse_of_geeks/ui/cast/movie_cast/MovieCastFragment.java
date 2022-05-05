package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.cast.movie_cast;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Cast;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.cast.CastViewModel;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters.MovieCastAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentMovieCastBinding;

public class MovieCastFragment extends Fragment
{
  private FragmentMovieCastBinding binding;
  private CastViewModel castViewModel;
  private final List<Cast> casts = new ArrayList<>();
  private RecyclerView recyclerView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    castViewModel = new ViewModelProvider(this).get(CastViewModel.class);

    binding = FragmentMovieCastBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    recyclerView = root.findViewById(R.id.movie_cast_rv);

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
      String id = MovieCastFragmentArgs.fromBundle(getArguments()).getMovieId();
      int movieId = Integer.parseInt(id);
      castViewModel.getMovieCast(movieId);
      recyclerView.hasFixedSize();
      recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
      MovieCastAdapter adapter = new MovieCastAdapter(casts);
      Observer<List<Cast>> update = adapter::updateMovieCastList;
      castViewModel.getMovieCast(movieId)
          .observe(getViewLifecycleOwner(), update);
      recyclerView.setAdapter(adapter);
    }
  }
}
