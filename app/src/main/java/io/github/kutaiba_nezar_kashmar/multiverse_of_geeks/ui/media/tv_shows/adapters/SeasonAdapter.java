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
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.TvShowSeasonResponse;

public class SeasonAdapter
    extends RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>
{
  private List<TvShowSeasonResponse> seasons;

  public SeasonAdapter(List<TvShowSeasonResponse> seasons)
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
    holder.seasonName.setText(seasons.get(position).getName());
    holder.seasonNumber
        .setText("Season " + seasons.get(position).getSeason_number());
    holder.seasonEpisodesNumber
        .setText("episode " + seasons.get(position).getEpisode_count());
    holder.seasonAirDate.setText(seasons.get(position).getAir_date());
    holder.seasonOverview.setText(seasons.get(position).getOverview());
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

  public void updateSeasonsList(final List<TvShowSeasonResponse> seasons)
  {
    this.seasons.clear();
    this.seasons = seasons;
    notifyDataSetChanged();
  }

  class SeasonViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView poster;
    private final TextView seasonName;
    private final TextView seasonNumber;
    private final TextView seasonEpisodesNumber;
    private final TextView seasonAirDate;
    private final TextView seasonOverview;
    private final Context context;

    public SeasonViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      poster = itemView.findViewById(R.id.season_poster);
      seasonName = itemView.findViewById(R.id.season_name);
      seasonNumber = itemView.findViewById(R.id.season_number);
      seasonEpisodesNumber = itemView.findViewById(R.id.season_episodes_num);
      seasonAirDate = itemView.findViewById(R.id.season_air_date);
      seasonOverview = itemView.findViewById(R.id.season_overview);
    }
  }
}
