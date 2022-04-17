package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.games.GamesPagerAdapter;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentGamesMainBinding;

public class MainGamesFragment extends Fragment
{
  private FragmentGamesMainBinding binding;
  private TabLayout tabLayout;
  private ViewPager2 viewPager;
  private GamesPagerAdapter adapter;
  private final String[] titles = new String[] {"Free to play"};
  public static final int PAGE_NUM = 1;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    binding = FragmentGamesMainBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    tabLayout = root.findViewById(R.id.games_tab_layout);
    viewPager = root.findViewById(R.id.games_view_pager);
    adapter = new GamesPagerAdapter(this);
    viewPager.setAdapter(adapter);
    new TabLayoutMediator(tabLayout, viewPager,
        (tab, position) -> tab.setText(titles[position])).attach();
    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}
