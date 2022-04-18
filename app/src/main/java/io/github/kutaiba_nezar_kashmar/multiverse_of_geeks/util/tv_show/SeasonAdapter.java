package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.tv_show;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.tv_responses.TvShowSeasonResponse;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class SeasonAdapter
    extends RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>
{
  private ArrayList<TvShowSeasonResponse> seasons;
  private OnClickListener listener;

  public SeasonAdapter(ArrayList<TvShowSeasonResponse> seasons)
  {
    this.seasons = seasons;
  }

  @NonNull
  @Override
  public SeasonViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.season_item, parent, false);
    return new SeasonViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull SeasonViewHolder holder, int position)
  {
    Glide.with(holder.context).load(
        "https://image.tmdb.org/t/p/w500" + seasons.get(position)
            .getPoster_path()).into(holder.poster);
  }

  @Override
  public int getItemCount()
  {
    if (seasons != null)
    {
      return seasons.size();
    }
    return 0;
  }

  public void updateSeasonsList(final ArrayList<TvShowSeasonResponse> seasons)
  {
    this.seasons.clear();
    this.seasons = seasons;
    notifyDataSetChanged();
  }

  public void setListener(OnClickListener listener)
  {
    this.listener = listener;
  }

  class SeasonViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView poster;
    private Context context;

    public SeasonViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      poster = itemView.findViewById(R.id.season_poster);
      itemView.setOnClickListener(view -> {
        listener.onClick(seasons.get(getBindingAdapterPosition()));
      });
    }
  }

  interface OnClickListener
  {
    void onClick(TvShowSeasonResponse season);
  }
}
