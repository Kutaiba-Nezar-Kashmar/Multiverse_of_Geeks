package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentTvShowsBinding;

public class TvShowsFragment extends Fragment
{
  private FragmentTvShowsBinding binding;
  private TVShowsViewModel tvShowsViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    tvShowsViewModel = new ViewModelProvider(this)
        .get(TVShowsViewModel.class);

    binding = FragmentTvShowsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    return root;
  }

  @Override public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}
