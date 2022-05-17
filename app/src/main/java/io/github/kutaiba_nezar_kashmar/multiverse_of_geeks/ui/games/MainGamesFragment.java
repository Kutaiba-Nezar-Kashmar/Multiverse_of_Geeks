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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentGamesMainBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters.GamesPagerAdapter;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.util.ZoomOutPageTransformer;

public class MainGamesFragment extends Fragment
{
  private FragmentGamesMainBinding binding;
  private final String[] titles = new String[] {"Free to play", "ALL GAMES",
      "Favorite", "Search"};
  public static final int PAGE_NUM = 4;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    binding = FragmentGamesMainBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    //Views
    TabLayout tabLayout = root.findViewById(R.id.games_tab_layout);
    ViewPager2 viewPager = root.findViewById(R.id.games_view_pager);
    viewPager.setPageTransformer(new ZoomOutPageTransformer());

    GamesPagerAdapter adapter = new GamesPagerAdapter(this);

    //Setup ViewPager2
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
