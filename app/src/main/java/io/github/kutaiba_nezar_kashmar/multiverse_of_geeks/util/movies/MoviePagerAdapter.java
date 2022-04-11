package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.movies;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies.MoviesFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows.TvShowsFragment;

public class MoviePagerAdapter extends FragmentStateAdapter
{
  public MoviePagerAdapter(@NonNull Fragment fragment)
  {
    super(fragment);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position)
  {
    Fragment fragment = new MoviesFragment();
    if (position == 0)
    {
      fragment = new MoviesFragment();
    }
    if (position == 1)
    {
      fragment = new TvShowsFragment();
    }
    return fragment;
  }

  @Override
  public int getItemCount()
  {
    return 2;
  }
}
