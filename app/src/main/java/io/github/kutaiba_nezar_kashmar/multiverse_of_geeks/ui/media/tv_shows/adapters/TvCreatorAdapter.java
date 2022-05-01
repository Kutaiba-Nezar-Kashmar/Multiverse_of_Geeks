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

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.TvShowCreatorResponse;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class TvCreatorAdapter
    extends RecyclerView.Adapter<TvCreatorAdapter.TvCreatorViewHolder>
{
  private ArrayList<TvShowCreatorResponse> tvShowCreatorResponses;

  public TvCreatorAdapter(
      ArrayList<TvShowCreatorResponse> tvShowCreatorResponses)
  {
    this.tvShowCreatorResponses = tvShowCreatorResponses;
  }

  @NonNull
  @Override
  public TvCreatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.tv_creator_item, parent, false);
    return new TvCreatorViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull TvCreatorViewHolder holder,
      int position)
  {
    holder.creatorName.setText(tvShowCreatorResponses.get(position).getName());
    Glide.with(holder.context).load(
        "https://image.tmdb.org/t/p/w500" + tvShowCreatorResponses.get(position)
            .getProfile_path()).into(holder.creatorPic);
  }

  @Override
  public int getItemCount()
  {
    if (tvShowCreatorResponses != null)
    {
      return tvShowCreatorResponses.size();
    }
    return 0;
  }

  public void updateCreatorList(final ArrayList<TvShowCreatorResponse> tvShowCreatorResponses)
  {
    this.tvShowCreatorResponses.clear();
    this.tvShowCreatorResponses = tvShowCreatorResponses;
    notifyDataSetChanged();
  }

  class TvCreatorViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView creatorName;
    private final ImageView creatorPic;
    private Context context;

    public TvCreatorViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      creatorName = itemView.findViewById(R.id.tv_creator_name);
      creatorPic = itemView.findViewById(R.id.tv_creator_image);
    }
  }
}
