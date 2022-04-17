package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.games;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.games_lists.FreeToPlayGamesFragment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.MainGamesFragment;

public class GamesPagerAdapter extends FragmentStateAdapter
{
  public GamesPagerAdapter(@NonNull Fragment fragment)
  {
    super(fragment);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position)
  {
    Fragment fragment = new FreeToPlayGamesFragment();
    if (position == 0)
    {
      fragment = new FreeToPlayGamesFragment();
    }
    return fragment;
  }

  @Override
  public int getItemCount()
  {
    return MainGamesFragment.PAGE_NUM;
  }
}