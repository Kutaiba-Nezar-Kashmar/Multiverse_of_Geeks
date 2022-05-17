package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Cast;

public class MovieCastAdapter
    extends RecyclerView.Adapter<MovieCastAdapter.MovieCastViewHolder>
{
  private List<Cast> casts;

  public MovieCastAdapter(List<Cast> casts)
  {
    this.casts = casts;
  }

  @NonNull
  @Override
  public MovieCastViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.movie_cast_item, parent, false);
    return new MovieCastViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MovieCastViewHolder holder,
      int position)
  {
    holder.name.setText(
        holder.context.getString(R.string.name_holder) + " " + casts.get(
            position).getName());
    holder.character.setText(
        holder.context.getString(R.string.character) + " " + casts.get(position)
            .getCharacter());
    holder.role.setText(
        holder.context.getString(R.string.role) + " " + casts.get(position)
            .getKnown_for_department());

    //set up image view from an API requires Glide
    Glide.with(holder.context).load(
        "https://image.tmdb.org/t/p/w500" + casts.get(position)
            .getProfile_path()).into(holder.pic);
  }

  @Override
  public int getItemCount()
  {
    if (casts != null)
    {
      return casts.size();
    }
    return 0;
  }

  //clear and reassign the cast list every time this method is called
  public void updateMovieCastList(final List<Cast> casts)
  {
    this.casts.clear();
    this.casts = casts;
    notifyDataSetChanged();
  }

  class MovieCastViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView name;
    private final TextView character;
    private final TextView role;
    private final ImageView pic;
    private final Context context;

    public MovieCastViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      name = itemView.findViewById(R.id.movie_cast_name);
      character = itemView.findViewById(R.id.movie_character);
      role = itemView.findViewById(R.id.movie_cast_role);
      pic = itemView.findViewById(R.id.movie_cast_image);
    }
  }
}
