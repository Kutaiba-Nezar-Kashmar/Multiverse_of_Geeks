package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GameScreenShots;

public class FreeToPlayScreenShotsAdapter extends
    RecyclerView.Adapter<FreeToPlayScreenShotsAdapter.FreeToPlayScreenShotsViewHolder>
{
  private List<GameScreenShots> gameScreenShots;

  public FreeToPlayScreenShotsAdapter(
      List<GameScreenShots> gameScreenShots)
  {
    this.gameScreenShots = gameScreenShots;
  }

  @NonNull
  @Override
  public FreeToPlayScreenShotsViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent, int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater
        .inflate(R.layout.free_to_play_screen_shot_item, parent, false);
    return new FreeToPlayScreenShotsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull FreeToPlayScreenShotsViewHolder holder,
      int position)
  {
    Glide.with(holder.context)
        .load(gameScreenShots.get(position).getImage())
        .into(holder.screenShot);
  }

  public void updateScreenShotList(
      final List<GameScreenShots> gameScreenShots)
  {
    this.gameScreenShots.clear();
    this.gameScreenShots = gameScreenShots;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount()
  {
    if (gameScreenShots != null)
    {
      return gameScreenShots.size();
    }
    return 0;
  }

  class FreeToPlayScreenShotsViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView screenShot;
    private Context context;

    public FreeToPlayScreenShotsViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      screenShot = itemView.findViewById(R.id.free_to_play_screen_shot);
    }
  }
}
