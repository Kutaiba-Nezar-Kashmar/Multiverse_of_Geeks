package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.movies;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class MoviesAdapter
    extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>
{
  private ArrayList<Movie> movies;
  private OnClickListener listener;

  public MoviesAdapter(ArrayList<Movie> movies)
  {
    this.movies = movies;
  }

  @NonNull @Override public ViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent, int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.movie_item, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder,
      int position)
  {
    //Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path()).into(imageView);
    holder.title.setText(movies.get(position).getTitle());
    Glide.with(holder.context).load("https://image.tmdb.org/t/p/w500" + movies.get(position).getPoster_path()).into(holder.poster);
  }

  public void updateMovieList(final ArrayList<Movie> movies)
  {
    this.movies.clear();
    this.movies = movies;
    notifyDataSetChanged();
  }

  @Override public int getItemCount()
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

  class ViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView title;
    private final ImageView poster;
    private Context context;

    public ViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      title = itemView.findViewById(R.id.movie_text_view);
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
