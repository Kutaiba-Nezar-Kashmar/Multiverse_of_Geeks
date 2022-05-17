package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.my_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentMyProfileBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.util.GlideApp;

public class MyProfileFragment extends Fragment
{
  private FragmentMyProfileBinding binding;
  private MyProfileViewModel myProfileViewModel;
  private ImageView myProfileImage;
  private TextView name;
  private TextView email;

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

    //Views
    myProfileImage = root.findViewById(R.id.my_profile_image);
    name = root.findViewById(R.id.person_name);
    email = root.findViewById(R.id.person_email);
    Button toEditFragButton = root.findViewById(R.id.to_edit_frag);
    Button signOutButton = root.findViewById(R.id.sign_out_but);

    checkIfSignedIn(root);

    //Setup editFragButton listener
    toEditFragButton.setOnClickListener(
        view -> Navigation.findNavController(root).navigate(
            MyProfileFragmentDirections.actionNavMyProfileToEditProfileNav()));

    //Setup signOutButton listener
    signOutButton.setOnClickListener(view -> myProfileViewModel.signOut());
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
            name.setText(firebaseUser.getDisplayName());
            email.setText(firebaseUser.getEmail());

            //Glide to set image to myProfileImage using storage reference
            GlideApp.with(view.getContext()).load(
                    myProfileViewModel.profileImagePath(firebaseUser.getUid()))
                .placeholder(R.drawable.avatar_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                .into(myProfileImage);
          }
        });
  }
}
