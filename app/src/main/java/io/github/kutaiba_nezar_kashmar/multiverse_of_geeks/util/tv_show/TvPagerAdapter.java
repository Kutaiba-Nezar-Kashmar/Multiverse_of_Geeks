package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.tv_show;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows.TvShowsMainFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows.tv_shows_lists.AiringTodayTvShowsFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows.tv_shows_lists.FavoriteTvShowFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows.tv_shows_lists.OnAirTvShowsFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows.tv_shows_lists.PopularTvShowsFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows.tv_shows_lists.SearchTvShowFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.tv_shows.tv_shows_lists.TopRatedTvShowsFragment;

public class TvPagerAdapter extends FragmentStateAdapter
{
  public TvPagerAdapter(@NonNull Fragment fragment)
  {
    super(fragment);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position)
  {
    Fragment fragment = new PopularTvShowsFragment();
    if (position == 0)
    {
      fragment = new PopularTvShowsFragment();
    }
    if (position == 1)
    {
      fragment = new TopRatedTvShowsFragment();
    }
    if (position == 2)
    {
      fragment = new OnAirTvShowsFragment();
    }
    if (position == 3)
    {
      fragment = new AiringTodayTvShowsFragment();
    }
    if (position == 4)
    {
      fragment = new FavoriteTvShowFragment();
    }
    if (position == 5)
    {
      fragment = new SearchTvShowFragment();
    }
    return fragment;
  }

  @Override
  public int getItemCount()
  {
    return TvShowsMainFragment.PAGE_NUM;
  }
}
