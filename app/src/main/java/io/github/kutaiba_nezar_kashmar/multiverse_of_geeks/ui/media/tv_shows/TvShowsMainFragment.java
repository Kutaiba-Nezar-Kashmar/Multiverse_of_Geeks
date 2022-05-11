package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows;

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
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentMainTvShowsBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters.TvPagerAdapter;

public class TvShowsMainFragment extends Fragment
{
  private FragmentMainTvShowsBinding binding;
  private TabLayout tabLayout;
  private ViewPager2 viewPager;
  private TvPagerAdapter adapter;
  private final String[] titles = new String[] {"POPULAR", "TOP RATED",
      "STREAMING", "STREAMING TODAY", "Favorite", "Search Tv Show"};
  public static final int PAGE_NUM = 6;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
  {
    binding = FragmentMainTvShowsBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    tabLayout = root.findViewById(R.id.tv_tab_layout);
    viewPager = root.findViewById(R.id.tv_view_pager);
    adapter = new TvPagerAdapter(this);
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
