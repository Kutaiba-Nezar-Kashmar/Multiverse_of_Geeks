package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment
{
  private FragmentAboutBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    binding = FragmentAboutBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    String action = Intent.ACTION_VIEW;

    //Views
    TextView facebook = root.findViewById(R.id.facebook_link);
    TextView github = root.findViewById(R.id.github_link);
    TextView linkedin = root.findViewById(R.id.linkedin_link);

    //Views click Listeners
    facebook.setOnClickListener(view -> {
      //set up facebook implicit intent
      Intent intent = new Intent(action,
          navToLinkIntent("https://www.facebook.com/kutaiba.kashmar"));
      startActivity(intent);
    });

    github.setOnClickListener(view -> {
      //set up github implicit intent
      Intent intent = new Intent(action,
          navToLinkIntent("https://github.com/Kutaiba-Nezar-Kashmar"));
      startActivity(intent);
    });

    linkedin.setOnClickListener(view -> {
      //set up linkedin implicit intent
      Intent intent = new Intent(action, navToLinkIntent(
          "https://www.linkedin.com/in/kutaiba-nezar-kashmar-5380a81b5/"));
      startActivity(intent);
    });

    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  //Parse a string to a uri
  private Uri navToLinkIntent(String uri)
  {
    return Uri.parse(uri);
  }
}
