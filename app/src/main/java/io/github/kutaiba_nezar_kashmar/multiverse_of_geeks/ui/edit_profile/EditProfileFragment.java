package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.edit_profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.UserProfileChangeRequest;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.MainActivity;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.databinding.FragmentEditProfileBinding;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.login.SignInActivity;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.my_profile.MyProfileFragmentDirections;

public class EditProfileFragment extends Fragment
{
  private FragmentEditProfileBinding binding;
  private EditProfileViewModel editProfileViewModel;
  private ImageView profileImage;
  private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
  private EditText userName;
  private EditText email;
  private EditText phone;
  private Button saveButton;
  private Button resetButton;
  private Button deleteButton;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    editProfileViewModel = new ViewModelProvider(this).get(
        EditProfileViewModel.class);

    binding = FragmentEditProfileBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    profileImage = root.findViewById(R.id.new_profile_image);
    userName = root.findViewById(R.id.new_name_field);
    email = root.findViewById(R.id.new_email_field_button);
    phone = root.findViewById(R.id.new_phone_field_button);
    saveButton = root.findViewById(R.id.save_changes_account_button);
    resetButton = root.findViewById(R.id.reset_password_button);
    deleteButton = root.findViewById(R.id.delete_account_button);
    checkIfSignedIn(root);
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
            saveButton.setOnClickListener(v -> {
              String emailInput = email.getText().toString().trim();
              String nameInput = userName.getText().toString();
              String phoneInput = email.getText().toString().trim();
/*
              if (emailInput.matches(emailInput) && emailInput.isEmpty())
              {
                Toast.makeText(getContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
              }
*/
              UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setDisplayName(
                  nameInput).build();
              firebaseUser.updateProfile(request);
              userName.getText().clear();
            });
          }
        });
  }
}
