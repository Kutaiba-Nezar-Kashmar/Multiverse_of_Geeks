package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.MoviesMainFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.movies_lists.BoxOfficeMoviesFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.movies_lists.FavoriteMoviesFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.movies_lists.PopularMoviesFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.movies_lists.SearchedMoviesFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.movies_lists.TopRatedMoviesFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.movies_lists.UpcomingMoviesFragment;

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
    Fragment fragment = new PopularMoviesFragment();
    if (position == 0)
    {
      fragment = new PopularMoviesFragment();
    }
    if (position == 1)
    {
      fragment = new TopRatedMoviesFragment();
    }
    if (position == 2)
    {
      fragment = new BoxOfficeMoviesFragment();
    }
    if (position == 3)
    {
      fragment = new UpcomingMoviesFragment();
    }
    if (position == 4)
    {
      fragment = new FavoriteMoviesFragment();
    }
    if (position == 5)
    {
      fragment = new SearchedMoviesFragment();
    }
    return fragment;
  }

  @Override
  public int getItemCount()
  {
    return MoviesMainFragment.PAGE_NUM;
  }
}
