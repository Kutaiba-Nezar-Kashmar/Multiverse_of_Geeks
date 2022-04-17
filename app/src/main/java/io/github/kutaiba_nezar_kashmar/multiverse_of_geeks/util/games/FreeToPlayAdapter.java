package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.games;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.FreeToPlayGameResponse;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class FreeToPlayAdapter
    extends RecyclerView.Adapter<FreeToPlayAdapter.FreeToPlayViewHolder>
{
  private ArrayList<FreeToPlayGameResponse> freeToPlayGames;

  public FreeToPlayAdapter(ArrayList<FreeToPlayGameResponse> freeToPlayGames)
  {
    this.freeToPlayGames = freeToPlayGames;
  }

  @NonNull
  @Override
  public FreeToPlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater
        .inflate(R.layout.free_to_play_game_item, parent, false);
    return new FreeToPlayViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull FreeToPlayViewHolder holder,
      int position)
  {
    Glide.with(holder.context)
        .load(freeToPlayGames.get(position).getThumbnail()).into(holder.poster);
  }

  public void updateFreeToPlayList(final ArrayList<FreeToPlayGameResponse> freeToPlayGames)
  {
    this.freeToPlayGames.clear();
    this.freeToPlayGames = freeToPlayGames;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount()
  {
    if (freeToPlayGames != null)
    {
      return freeToPlayGames.size();
    }
    return 0;
  }

  class FreeToPlayViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView poster;
    private Context context;

    public FreeToPlayViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      poster = itemView.findViewById(R.id.free_to_play_image);
    }
  }
}
