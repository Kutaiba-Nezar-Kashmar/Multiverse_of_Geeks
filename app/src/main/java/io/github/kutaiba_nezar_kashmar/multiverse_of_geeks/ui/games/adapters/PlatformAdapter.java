package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters;

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
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.PlatformsResponse;

public class PlatformAdapter
    extends RecyclerView.Adapter<PlatformAdapter.PlatformViewHolder>
{
  private List<PlatformsResponse> platformsResponses;

  public PlatformAdapter(List<PlatformsResponse> platformsResponses)
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
      final List<PlatformsResponse> platformsResponses)
  {
    this.platformsResponses.clear();
    this.platformsResponses = platformsResponses;
    notifyDataSetChanged();
  }

  class PlatformViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView name;
    private final ImageView poster;
    private final Context context;

    public PlatformViewHolder(@NonNull View itemView)
    {
      super(itemView);
      name = itemView.findViewById(R.id.platform_name);
      poster = itemView.findViewById(R.id.platform_image);
      context = itemView.getContext();
    }
  }
}
