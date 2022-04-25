package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment
{

  private FragmentHomeBinding binding;
  private HomeViewModel homeViewModel;
  private TextView rawgLink;
  private TextView tmdbLink;
  private ImageView rawgImage;
  private ImageView tmdbImage;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    rawgLink = root.findViewById(R.id.rawg_link);
    tmdbLink = root.findViewById(R.id.tmdb_link);
    rawgImage = root.findViewById(R.id.rawg_ref_image);
    tmdbImage = root.findViewById(R.id.tmdb_ref_image);
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
    rawgLink.setText("https://rawg.io/");
    tmdbLink.setText("https://www.themoviedb.org/");
    Glide.with(view.getContext()).load(
        "https://rawg.io/assets/images/cover.117cc320ec2800b9b12092ca23d6e86d.png")
        .into(rawgImage);
    Glide.with(view.getContext()).load(
        "https://play-lh.googleusercontent.com/IO3niAyss5tFXAQP176P0Jk5rg_A_hfKPNqzC4gb15WjLPjo5I-f7oIZ9Dqxw2wPBAg")
        .into(tmdbImage);
  }
}