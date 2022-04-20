package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.games;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.games_responses.games.PlatformsResponse;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class PlatformAdapter
    extends RecyclerView.Adapter<PlatformAdapter.PlatformViewHolder>
{
  private ArrayList<PlatformsResponse> platformsResponses;

  public PlatformAdapter(ArrayList<PlatformsResponse> platformsResponses)
  {
    this.platformsResponses = platformsResponses;
  }

  @NonNull
  @Override
  public PlatformViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.platform_item, parent, false);
    return new PlatformViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull PlatformViewHolder holder, int position)
  {
    holder.name.setText(platformsResponses.get(position).getPlatform().getName());
    Glide.with(holder.context)
        .load(platformsResponses.get(position).getPlatform().getImage_background())
        .into(holder.poster);
  }

  @Override
  public int getItemCount()
  {
    if (platformsResponses != null)
    {
      return platformsResponses.size();
    }
    return 0;
  }

  public void updatePlatformList(
      final ArrayList<PlatformsResponse> platformsResponses)
  {
    this.platformsResponses.clear();
    this.platformsResponses = platformsResponses;
    notifyDataSetChanged();
  }

  class PlatformViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView name;
    private final ImageView poster;
    private Context context;

    public PlatformViewHolder(@NonNull View itemView)
    {
      super(itemView);
      name = itemView.findViewById(R.id.platform_name);
      poster = itemView.findViewById(R.id.platform_image);
      context = itemView.getContext();
    }
  }
}
