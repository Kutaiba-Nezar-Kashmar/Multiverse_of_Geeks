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
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;

public class FavoriteTvShowAdapter
    extends RecyclerView.Adapter<FavoriteTvShowAdapter.FavTvViewHolder>
{
  private List<SingleTvShowResponse> tvShows;

  public FavoriteTvShowAdapter(List<SingleTvShowResponse> tvShows)
  {
    this.tvShows = tvShows;
  }

  @NonNull
  @Override
  public FavTvViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.tv_show_item, parent, false);
    return new FavTvViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull FavTvViewHolder holder, int position)
  {
    //Glide to set image in poster
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

  public void updateTvShowList(final List<SingleTvShowResponse> tvShows)
  {
    this.tvShows.clear();
    this.tvShows = tvShows;
    notifyDataSetChanged();
  }

  class FavTvViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView poster;
    private final Context context;

    public FavTvViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      poster = itemView.findViewById(R.id.tv_image);
    }
  }
}
