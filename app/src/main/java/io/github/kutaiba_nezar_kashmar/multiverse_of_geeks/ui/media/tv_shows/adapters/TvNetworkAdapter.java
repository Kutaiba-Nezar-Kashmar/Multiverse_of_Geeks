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
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.TvShowNetworkResponse;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class TvNetworkAdapter
    extends RecyclerView.Adapter<TvNetworkAdapter.TvNetworkHolder>
{
  private List<TvShowNetworkResponse> tvShowNetworkResponses;

  public TvNetworkAdapter(
      List<TvShowNetworkResponse> tvShowNetworkResponses)
  {
    this.tvShowNetworkResponses = tvShowNetworkResponses;
  }

  @NonNull
  @Override
  public TvNetworkHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.tv_network_item, parent, false);
    return new TvNetworkHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull TvNetworkHolder holder, int position)
  {
    holder.networkName.setText(tvShowNetworkResponses.get(position).getName());
    System.out.println("-----------------------------------" + tvShowNetworkResponses.get(position)
        .getLogo_path() + "----------------------------------");
    Glide.with(holder.context).load(
        "https://image.tmdb.org/t/p/w500" + tvShowNetworkResponses.get(position)
            .getLogo_path()).into(holder.networkLogo);
  }

  @Override
  public int getItemCount()
  {
    if (tvShowNetworkResponses != null)
    {
      return tvShowNetworkResponses.size();
    }
    return 0;
  }

  public void updateNetworkList(
      final List<TvShowNetworkResponse> tvShowNetworkResponses)
  {
    this.tvShowNetworkResponses.clear();
    this.tvShowNetworkResponses = tvShowNetworkResponses;
    notifyDataSetChanged();
  }

  class TvNetworkHolder extends RecyclerView.ViewHolder
  {
    private final TextView networkName;
    private final ImageView networkLogo;
    private Context context;

    public TvNetworkHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      networkName = itemView.findViewById(R.id.tv_network_name);
      networkLogo = itemView.findViewById(R.id.tv_network_logo);
    }
  }
}
