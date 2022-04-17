package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentTvShowSeasonsBinding;

public class TvShowSeasonsFragment extends Fragment
{
  private FragmentTvShowSeasonsBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    binding = FragmentTvShowSeasonsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}
