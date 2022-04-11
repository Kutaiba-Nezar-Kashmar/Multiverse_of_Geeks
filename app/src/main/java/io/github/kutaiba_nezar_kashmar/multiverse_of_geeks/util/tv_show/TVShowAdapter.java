package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.tv_show;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.TvShow;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class TVShowAdapter
    extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>
{
  private ArrayList<TvShow> tvShows;
  private OnClickListener listener;

  public TVShowAdapter(ArrayList<TvShow> tvShows)
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
    holder.title.setText(tvShows.get(position).getName());
    //Glide is required to fill image view from API
    Glide.with(holder.context).load(
        "https://image.tmdb.org/t/p/w500" + tvShows.get(position)
            .getPoster_path()).into(holder.poster);
    holder.tvRatting
        .setText(" " + String.valueOf(tvShows.get(position).getVote_average()));
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
  public void updateTVShowList(final ArrayList<TvShow> tvShows)
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
    private final TextView title;
    private final ImageView poster;
    private final TextView tvRatting;
    private Context context;

    public TVShowViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      title = itemView.findViewById(R.id.tv_text_view);
      poster = itemView.findViewById(R.id.tv_image);
      tvRatting = itemView.findViewById(R.id.tv_ratting);

      itemView.setOnClickListener(view -> {
        listener.onClick(tvShows.get(getBindingAdapterPosition()));
      });
    }
  }

  public interface OnClickListener
  {
    void onClick(TvShow tvShow);
  }
}
