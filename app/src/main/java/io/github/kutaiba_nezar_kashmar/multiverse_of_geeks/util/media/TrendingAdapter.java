package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.media;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Trending;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class TrendingAdapter
    extends RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>
{
  private ArrayList<Trending> trendingList;

  public TrendingAdapter(ArrayList<Trending> trendingList)
  {
    this.trendingList = trendingList;
  }

  @NonNull
  @Override
  public TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.trending_media_item, parent, false);
    return new TrendingAdapter.TrendingViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull TrendingViewHolder holder, int position)
  {
    holder.mediaType
        .setText(trendingList.get(position).getMedia_type());
    //Glide is required to fill image view from API
    Glide.with(holder.context).load(
        "https://image.tmdb.org/t/p/w500" + trendingList.get(position)
            .getPoster_path()).into(holder.poster);
    holder.mediaRating.setText(
        " " + String.valueOf(trendingList.get(position).getVote_average()));
    holder.mediaPopularity.setText(
        " " + String.valueOf(trendingList.get(position).getPopularity()));
  }

  @Override
  public int getItemCount()
  {
    if (trendingList != null)
    {
      return trendingList.size();
    }
    return 0;
  }

  public void updateMovieList(final ArrayList<Trending> trendingList)
  {
    this.trendingList.clear();
    this.trendingList = trendingList;
    notifyDataSetChanged();
  }

  class TrendingViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView poster;
    private final TextView mediaRating;
    private final TextView mediaPopularity;
    private final TextView mediaType;
    private Context context;

    public TrendingViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      poster = itemView.findViewById(R.id.trending_image);
      mediaRating = itemView.findViewById(R.id.trending_ratting);
      mediaPopularity = itemView.findViewById(R.id.trending_popularity);
      mediaType = itemView.findViewById(R.id.media_type);
    }
  }
}
