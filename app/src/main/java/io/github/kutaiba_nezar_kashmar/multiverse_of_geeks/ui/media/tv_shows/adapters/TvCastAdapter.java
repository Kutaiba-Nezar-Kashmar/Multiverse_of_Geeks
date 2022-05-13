package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters;

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

public class TvCastAdapter
    extends RecyclerView.Adapter<TvCastAdapter.TvCastViewHolder>
{
  private List<Cast> casts;

  public TvCastAdapter(List<Cast> casts)
  {
    this.casts = casts;
  }

  @NonNull
  @Override
  public TvCastViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.tv_show_cast_item, parent, false);
    return new TvCastViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull TvCastViewHolder holder, int position)
  {
    holder.name.setText(
        holder.context.getString(R.string.name_holder) + " " + casts
            .get(position).getName());
    holder.character.setText(
        holder.context.getString(R.string.character) + " " + casts.get(position)
            .getCharacter());
    holder.role.setText(
        holder.context.getString(R.string.role) + " " + casts.get(position)
            .getKnown_for_department());
    //Glide is required to fill image view from API
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
  public void updateTvCastList(final List<Cast> casts)
  {
    this.casts.clear();
    this.casts = casts;
    notifyDataSetChanged();
  }

  class TvCastViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView name;
    private final TextView character;
    private final TextView role;
    private final ImageView pic;
    private final Context context;

    public TvCastViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      name = itemView.findViewById(R.id.tv_cast_name);
      character = itemView.findViewById(R.id.tv_character);
      role = itemView.findViewById(R.id.tv_cast_role);
      pic = itemView.findViewById(R.id.tv_cast_image);
    }
  }
}
