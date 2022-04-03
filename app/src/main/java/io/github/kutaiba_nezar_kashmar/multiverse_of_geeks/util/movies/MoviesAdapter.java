package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Movie;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>
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
    holder.textView.setText(movies.get(position).getTitle());
    holder.textView.setBackgroundResource(R.drawable.action_back);
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
    private final CardView cardView;
    private final TextView textView;
    public ViewHolder(@NonNull View itemView)
    {
      super(itemView);
      cardView = itemView.findViewById(R.id.movie_card_view);
      textView = itemView.findViewById(R.id.movie_text_view);

      itemView.setOnClickListener(view ->
      {
        listener.onClick(movies.get(getBindingAdapterPosition()));
      });
    }
  }

  public interface OnClickListener
  {
    void onClick(Movie movie);
  }
}
