package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepository;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.user.UserRepositoryImpl;
import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{

  private AppBarConfiguration mAppBarConfiguration;
  private ActivityMainBinding binding;
  private NavController navController;
  private UserRepository userRepository;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    userRepository = UserRepositoryImpl.getInstance(getApplication());
    setSupportActionBar(binding.appBarMain.toolbar);

    DrawerLayout drawer = binding.drawerLayout;
    NavigationView navigationView = binding.navView;
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    //Here goes the nave items, remember adding the relevant ones
    mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home,
        R.id.nav_my_profile, R.id.nav_main_movies, R.id.nav_tv, R.id.nav_games)
        .setOpenableLayout(drawer).build();
    navController = Navigation
        .findNavController(this, R.id.nav_host_fragment_content_main);
    NavigationUI.setupActionBarWithNavController(this, navController,
        mAppBarConfiguration);
    NavigationUI.setupWithNavController(navigationView, navController);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item)
  {
    if (item.getItemId() == R.id.action_sign_out)
    {
      signOut();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void signOut()
  {
    userRepository.signOut();
    Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
  }

  @Override
  public boolean onSupportNavigateUp()
  {
    NavController navController = Navigation
        .findNavController(this, R.id.nav_host_fragment_content_main);
    return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super
        .onSupportNavigateUp();
  }
}