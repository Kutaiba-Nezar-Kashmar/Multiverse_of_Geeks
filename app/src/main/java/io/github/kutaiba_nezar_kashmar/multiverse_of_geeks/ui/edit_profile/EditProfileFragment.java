package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.edit_profile;

import static android.content.Intent.ACTION_GET_CONTENT;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Objects;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentEditProfileBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.util.GlideApp;

public class EditProfileFragment extends Fragment
{
  private FragmentEditProfileBinding binding;
  private EditProfileViewModel editProfileViewModel;
  private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
  private String userId;
  private Uri uri = null;
  private ImageView profileImage;
  private EditText userName;
  private EditText email;
  private Button saveButton;
  private Button deleteButton;
  private Button resetButton;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    editProfileViewModel = new ViewModelProvider(this).get(
        EditProfileViewModel.class);

    binding = FragmentEditProfileBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

    //Views
    profileImage = root.findViewById(R.id.new_profile_image);
    userName = root.findViewById(R.id.new_name_field);
    email = root.findViewById(R.id.new_email_field_button);
    saveButton = root.findViewById(R.id.save_changes_account_button);
    deleteButton = root.findViewById(R.id.delete_account_button);
    resetButton = root.findViewById(R.id.reset_password_button);
    Button editPic = root.findViewById(R.id.edit_profile_piv_button);
    checkIfSignedIn(root);

    //Setup ActivityResultLauncher for Gallery Intent
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(), result -> {
          if (result.getResultCode() == Activity.RESULT_OK)
          {
            uri = Objects.requireNonNull(result.getData()).getData();
            editProfileViewModel.uploadToFirebaseStorage(uri, userId);
          }
        });

    //EditPic button listener
    editPic.setOnClickListener(v -> {
      //Define gallery update
      Intent openGallery = new Intent();
      openGallery.setType("image/*");
      openGallery.setAction(ACTION_GET_CONTENT);
      activityResultLauncher.launch(openGallery);
    });

    setSaveButton();
    setDeleteButton();
    setResetButton();

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
    editProfileViewModel.getCurrentUser()
        .observe(getViewLifecycleOwner(), firebaseUser -> {
          if (firebaseUser == null)
          {
            Navigation.findNavController(view).navigate(
                EditProfileFragmentDirections.actionEditProfileNavToNavHome());
            Toast.makeText(getContext(), "Please Login First",
                Toast.LENGTH_SHORT).show();
          }
          else
          {
            userId = firebaseUser.getUid();

            //Set ProfileImage using glide and storage reference
            GlideApp.with(view.getContext()).load(
                    editProfileViewModel.profileImagePath(firebaseUser.getUid()))
                .placeholder(R.drawable.avatar_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
                .into(profileImage);
          }
        });
  }

  private void setSaveButton()
  {
    saveButton.setOnClickListener(v -> {
      String emailInput = email.getText().toString().trim();
      String nameInput = userName.getText().toString();

      if (emailInput.matches(emailPattern) && !emailInput.isEmpty())
      {
        Objects.requireNonNull(editProfileViewModel.getCurrentUser().getValue())
            .updateEmail(emailInput);
      }
      userName.getText().clear();
      email.getText().clear();
      editProfileViewModel.updateProfile(uri, nameInput);
    });
  }

  private void setDeleteButton()
  {
    deleteButton.setOnClickListener(view1 -> {
      editProfileViewModel.deleteAccount();
      Toast.makeText(getContext(), editProfileViewModel.getNotification(),
          Toast.LENGTH_SHORT).show();
    });
  }

  private void setResetButton()
  {
    resetButton.setOnClickListener(
        view1 -> editProfileViewModel.resetPassword());
  }
}