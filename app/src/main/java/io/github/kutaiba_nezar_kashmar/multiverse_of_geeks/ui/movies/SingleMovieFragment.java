package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import io.github.kutaiba_nezar_kashmar.newapp.R;
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentSingleMovieBinding;

public class SingleMovieFragment extends Fragment
{
  private FragmentSingleMovieBinding binding;
  private MoviesViewModel moviesViewModel;
  private TextView textView;
  private int movieId;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState)
  {
    moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

    binding = FragmentSingleMovieBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    textView = root.findViewById(R.id.movie_id);

    return root;
  }

  @Override public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    if (getArguments() != null)
    {
      String id = SingleMovieFragmentArgs.fromBundle(getArguments()).getMovieIdArg();
      movieId = Integer.parseInt(id);
      textView.setText(String.valueOf(movieId));
    }
  }

  @Override public void onDestroyView()
  {
    super.onDestroyView();
    binding = null;
  }
}
