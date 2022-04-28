package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.create_account;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.edit_profile.EditProfileViewModel;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentCreateProfileBinding;

public class CreateAccountFragment extends Fragment
{
  private FragmentCreateProfileBinding binding;
  private CreateAccountViewModel createAccountViewModel;
  private EditText email;
  private EditText password;
  private Button createAccount;
  private FirebaseAuth firebaseAuth;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    createAccountViewModel = new ViewModelProvider(this)
        .get(CreateAccountViewModel.class);

    binding = FragmentCreateProfileBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    email = root.findViewById(R.id.email_field);
    password = root.findViewById(R.id.password_field);
    createAccount = root.findViewById(R.id.create_account_button);
    return root;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    createAccount.setOnClickListener(view1 -> {
      createNewUser();
    });
  }

  private void createNewUser()
  {
    String email = this.email.getText().toString();
    String password = this.password.getText().toString();
    if (!email.isEmpty() && !password.isEmpty())
    {
      createAccountViewModel.registerUser(email, password);
    }
    else
    {
      Toast.makeText(getContext(), "email and password are required", Toast.LENGTH_SHORT).show();
    }
  }
}
