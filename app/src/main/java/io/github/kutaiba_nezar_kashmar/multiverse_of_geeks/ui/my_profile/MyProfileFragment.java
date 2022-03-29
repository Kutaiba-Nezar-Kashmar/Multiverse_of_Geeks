package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.my_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentMyProfileBinding;

public class MyProfileFragment extends Fragment implements View.OnClickListener
{
  private FragmentMyProfileBinding binding;
  ImageView editIcon;

  public MyProfileFragment()
  {
  }

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    MyProfileViewModel myProfileViewModel =
        new ViewModelProvider(this).get(MyProfileViewModel.class);

    binding = FragmentMyProfileBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    return root;
  }

  @Override public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  @Override public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    Navigation.findNavController(view);
    editIcon = (ImageView) view.findViewById(R.id.edit_icon_button);
    editIcon.setOnClickListener(this);
  }

  @Override public void onClick(View view)
  {
    Navigation.findNavController(view).navigate(R.id.edit_profile_nav);
  }
}
