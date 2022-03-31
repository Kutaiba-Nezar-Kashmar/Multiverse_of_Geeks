package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentGamesBinding;

public class GamesFragment extends Fragment
{
  private FragmentGamesBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    GamesViewModel moviesViewModel = new ViewModelProvider(this)
        .get(GamesViewModel.class);

    binding = FragmentGamesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    return root;
  }

  @Override public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}