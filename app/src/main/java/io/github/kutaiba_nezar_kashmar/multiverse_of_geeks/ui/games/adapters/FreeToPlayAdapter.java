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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play.AllFreeToPlayGamesResponse;

public class FreeToPlayAdapter
    extends RecyclerView.Adapter<FreeToPlayAdapter.FreeToPlayViewHolder>
{
  private List<AllFreeToPlayGamesResponse> freeToPlayGames;
  private OnClickListener listener;

  public FreeToPlayAdapter(
      List<AllFreeToPlayGamesResponse> freeToPlayGames)
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

  public void updateFreeToPlayList(
      final List<AllFreeToPlayGamesResponse> freeToPlayGames)
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

  public void setListener(OnClickListener listener)
  {
    this.listener = listener;
  }

  class FreeToPlayViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView poster;
    private final Context context;

    public FreeToPlayViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      poster = itemView.findViewById(R.id.free_to_play_image);

      itemView.setOnClickListener(view -> {
        listener.onClick(freeToPlayGames.get(getBindingAdapterPosition()));
      });
    }
  }

  public interface OnClickListener
  {
    void onClick(AllFreeToPlayGamesResponse allFreeToPlayGamesResponse);
  }
}
