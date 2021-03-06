package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.tv_shows.adapters;

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
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.TvShow;

public class TVShowAdapter
    extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>
{
  private List<TvShow> tvShows;
  private OnClickListener listener;

  public TVShowAdapter(List<TvShow> tvShows)
  {
    this.tvShows = tvShows;
  }

  @NonNull
  @Override
  public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.tv_show_item, parent, false);
    return new TVShowViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position)
  {
    //Glide is required to fill image view from API
    Glide.with(holder.context).load(
        "https://image.tmdb.org/t/p/w500" + tvShows.get(position)
            .getPoster_path()).into(holder.poster);
  }

  @Override
  public int getItemCount()
  {
    if (tvShows != null)
    {
      return tvShows.size();
    }
    return 0;
  }

  //clear and reassign the cast list every time this method is called
  public void updateTVShowList(final List<TvShow> tvShows)
  {
    this.tvShows.clear();
    this.tvShows = tvShows;
    notifyDataSetChanged();
  }

  public void setListener(OnClickListener listener)
  {
    this.listener = listener;
  }

  class TVShowViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView poster;
    private final Context context;

    public TVShowViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      poster = itemView.findViewById(R.id.tv_image);

      itemView.setOnClickListener(
          view -> listener.onClick(tvShows.get(getBindingAdapterPosition())));
    }
  }

  public interface OnClickListener
  {
    void onClick(TvShow tvShow);
  }
}
