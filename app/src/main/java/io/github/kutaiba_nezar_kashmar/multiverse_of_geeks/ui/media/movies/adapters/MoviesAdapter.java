package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Movie;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class MoviesAdapter
    extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>
{
  private List<Movie> movies;
  private OnClickListener listener;

  public MoviesAdapter(List<Movie> movies)
  {
    this.movies = movies;
  }

  @NonNull
  @Override
  public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.movie_item, parent, false);
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MovieViewHolder holder, int position)
  {
    Glide.with(holder.context).load(
        "https://image.tmdb.org/t/p/w500" + movies.get(position)
            .getPoster_path()).into(holder.poster);
  }

  //clear and reassign the cast list every time this method is called
  public void updateMovieList(final List<Movie> movies)
  {
    this.movies.clear();
    this.movies = movies;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount()
  {
    if (movies != null)
    {
      return movies.size();
    }
    return 0;
  }

  public void setListener(OnClickListener listener)
  {
    this.listener = listener;
  }

  class MovieViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView poster;
    private Context context;

    public MovieViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      poster = itemView.findViewById(R.id.movie_image);

      itemView.setOnClickListener(view -> {
        listener.onClick(movies.get(getBindingAdapterPosition()));
      });
    }
  }

  public interface OnClickListener
  {
    void onClick(Movie movie);
  }
}
