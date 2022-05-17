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
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GamesDevelopersResponse;

public class DeveloperAdapter
    extends RecyclerView.Adapter<DeveloperAdapter.DeveloperViewHolder>
{
  private List<GamesDevelopersResponse> developersResponses;

  public DeveloperAdapter(List<GamesDevelopersResponse> developersResponses)
  {
    this.developersResponses = developersResponses;
  }

  @NonNull
  @Override
  public DeveloperViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.game_developer_item, parent, false);
    return new DeveloperViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DeveloperViewHolder holder,
      int position)
  {
    holder.name.setText(developersResponses.get(position).getName());

    //Glide to set image poster
    Glide.with(holder.context)
        .load(developersResponses.get(position).getImage_background())
        .into(holder.poster);
  }

  @Override
  public int getItemCount()
  {
    if (developersResponses != null)
    {
      return developersResponses.size();
    }
    return 0;
  }

  public void updateDeveloperList(
      final List<GamesDevelopersResponse> developersResponses)
  {
    this.developersResponses.clear();
    this.developersResponses = developersResponses;
    notifyDataSetChanged();
  }

  class DeveloperViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView name;
    private final ImageView poster;
    private final Context context;

    public DeveloperViewHolder(@NonNull View itemView)
    {
      super(itemView);
      name = itemView.findViewById(R.id.developer_name);
      poster = itemView.findViewById(R.id.developer_image);
      context = itemView.getContext();
    }
  }
}
