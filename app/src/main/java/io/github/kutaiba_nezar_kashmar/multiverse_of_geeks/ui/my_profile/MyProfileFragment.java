package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.my_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.MainActivity;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentMyProfileBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login.SignInActivity;

public class MyProfileFragment extends Fragment
{
  private FragmentMyProfileBinding binding;
  private MyProfileViewModel myProfileViewModel;
  private ImageView myProfileImage;
  private TextView name;
  private TextView email;
  private Button toEditFragButton;
  private Button signOutButton;

  public MyProfileFragment()
  {
  }

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    myProfileViewModel = new ViewModelProvider(this).get(
        MyProfileViewModel.class);

    binding = FragmentMyProfileBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    myProfileImage = root.findViewById(R.id.my_profile_image);
    name = root.findViewById(R.id.person_name);
    email = root.findViewById(R.id.person_email);
    toEditFragButton = root.findViewById(R.id.to_edit_frag);
    signOutButton = root.findViewById(R.id.sign_out_but);
    checkIfSignedIn(root);
    toEditFragButton.setOnClickListener(view -> {
      Navigation.findNavController(root).navigate(
          MyProfileFragmentDirections.actionNavMyProfileToEditProfileNav());
    });
    signOutButton.setOnClickListener(view -> {
      myProfileViewModel.signOut();
    });
    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  private void checkIfSignedIn(View view)
  {
    myProfileViewModel.getCurrentUser()
        .observe(getViewLifecycleOwner(), firebaseUser -> {
          if (firebaseUser == null)
          {
            Navigation.findNavController(view).navigate(
                MyProfileFragmentDirections.actionNavMyProfileToNavHome());
            Toast.makeText(getContext(), "Please Login First",
                Toast.LENGTH_SHORT).show();
          }
          else
          {
            System.out.println("+++++++++++++++++++///////////////////++++++++++++" + firebaseUser.getDisplayName());
            name.setText(firebaseUser.getDisplayName());
            email.setText(firebaseUser.getEmail());
            Glide.with(view.getContext()).load(firebaseUser.getPhotoUrl())
                .into(myProfileImage);
          }
        });
  }
}
