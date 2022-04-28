package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Game;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class FavoriteGameAdapter
    extends RecyclerView.Adapter<FavoriteGameAdapter.FavGamesViewHolder>
{
  private List<Game> games;

  public FavoriteGameAdapter(List<Game> games)
  {
    this.games = games;
  }

  @NonNull
  @Override
  public FavGamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.games_item, parent, false);
    return new FavGamesViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull FavGamesViewHolder holder, int position)
  {
    Glide.with(holder.context).load(games.get(position).getBackground_image())
        .into(holder.poster);
  }

  @Override
  public int getItemCount()
  {
    if (games != null)
    {
      return games.size();
    }
    return 0;
  }

  public void updateGameList(final List<Game> games)
  {
    this.games.clear();
    this.games = games;
    notifyDataSetChanged();
  }

  class FavGamesViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView poster;
    private Context context;

    public FavGamesViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      ;
      poster = itemView.findViewById(R.id.game_poster);
    }
  }
}
