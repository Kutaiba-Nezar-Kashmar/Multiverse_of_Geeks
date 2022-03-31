package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.movies;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.MovieGenre;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class MovieGenreAdapter extends RecyclerView.Adapter<MovieGenreAdapter.ViewHolder>
{
  private ArrayList<MovieGenre> movies;
  private OnClickListener listener;

  public MovieGenreAdapter(ArrayList<MovieGenre> movies)
  {
    this.movies = movies;
  }

  @NonNull @Override public ViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent, int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.movie_genre_item, parent, false);
    return new ViewHolder(view);
  }

  public void setListener(OnClickListener listener)
  {
    this.listener = listener;
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder,
      int position)
  {
    holder.textView.setText(movies.get(position).getGenreName());

    String genre = movies.get(position).getGenreName();

    if (genre.equals("Action"))
    {
      holder.textView.setBackgroundResource(R.drawable.action_back);
    }
    else if (genre.equals("Adventure"))
    {
      holder.textView.setBackgroundResource(R.drawable.adventure_back);
    }
    else if (genre.equals("Comedy"))
    {
      holder.textView.setBackgroundResource(R.drawable.comedy_back);
    }
    else if (genre.equals("Horror"))
    {
      holder.textView.setBackgroundResource(R.drawable.horror_back);
    }
    else if (genre.equals("Romance"))
    {
      holder.textView.setBackgroundResource(R.drawable.romance_back);
    }
    else if (genre.equals("Animated"))
    {
      holder.textView.setBackgroundResource(R.drawable.animated_back);
    }
  }

  @Override public int getItemCount()
  {
    return movies.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder
  {
    private final CardView cardView;
    private final TextView textView;

    public ViewHolder(@NonNull View itemView)
    {
      super(itemView);
      cardView = itemView.findViewById(R.id.genre_card_view);
      textView = itemView.findViewById(R.id.genre_text_view);


      itemView.setOnClickListener(view ->
      {
        listener.onClick(movies.get(getBindingAdapterPosition()));
      });
    }
  }

  public interface OnClickListener
  {
    void onClick(MovieGenre movieGenre);
  }
}
