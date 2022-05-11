package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses.SingleMovieResponse;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavMovieViewHolder>
{
  private List<SingleMovieResponse> movies;

  public FavoriteMovieAdapter(List<SingleMovieResponse> movies)
  {
    this.movies = movies;
  }

  @NonNull
  @Override
  public FavMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.movie_item, parent, false);
    return new FavMovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull FavMovieViewHolder holder, int position)
  {
    Glide.with(holder.context).load(
        "https://image.tmdb.org/t/p/w500" + movies.get(position)
            .getPoster_path()).into(holder.poster);
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

  public void updateMovieList(final List<SingleMovieResponse> movies)
  {
    this.movies.clear();
    this.movies = movies;
    notifyDataSetChanged();
  }

  class FavMovieViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView poster;
    private Context context;
    public FavMovieViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      poster = itemView.findViewById(R.id.movie_image);
    }
  }
}
