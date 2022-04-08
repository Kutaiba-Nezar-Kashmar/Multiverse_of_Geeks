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
import androidx.lifecycle.ViewModelProvider;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.edit_profile.EditProfileViewModel;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment
{
  private FragmentAboutBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    EditProfileViewModel editProfileViewModel = new ViewModelProvider(this)
        .get(EditProfileViewModel.class);

    binding = FragmentAboutBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    String action = Intent.ACTION_VIEW;
    TextView facebook = root.findViewById(R.id.facebook_link);
    TextView github = root.findViewById(R.id.github_link);
    TextView linkedin = root.findViewById(R.id.linkedin_link);

    facebook.setOnClickListener(view -> {
      Intent intent = new Intent(action,
          navToLinkIntent("https://www.facebook.com/kutaiba.kashmar"));
      startActivity(intent);
    });

    github.setOnClickListener(view -> {
      Intent intent = new Intent(action,
          navToLinkIntent("https://github.com/Kutaiba-Nezar-Kashmar"));
      startActivity(intent);
    });

    linkedin.setOnClickListener(view -> {
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

  private Uri navToLinkIntent(String uri)
  {
    return Uri.parse(uri);
  }
}
