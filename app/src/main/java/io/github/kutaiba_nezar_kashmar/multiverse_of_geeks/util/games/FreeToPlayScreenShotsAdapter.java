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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.FreeToPlayGameScreenShots;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class FreeToPlayScreenShotsAdapter extends
    RecyclerView.Adapter<FreeToPlayScreenShotsAdapter.FreeToPlayScreenShotsViewHolder>
{
  private ArrayList<FreeToPlayGameScreenShots> freeToPlayGameScreenShots;

  public FreeToPlayScreenShotsAdapter(
      ArrayList<FreeToPlayGameScreenShots> freeToPlayGameScreenShots)
  {
    this.freeToPlayGameScreenShots = freeToPlayGameScreenShots;
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
        .load(freeToPlayGameScreenShots.get(position).getImage())
        .into(holder.screenShot);
  }

  public void updateScreenShotList(
      final ArrayList<FreeToPlayGameScreenShots> freeToPlayGameScreenShots)
  {
    this.freeToPlayGameScreenShots.clear();
    ;
    this.freeToPlayGameScreenShots = freeToPlayGameScreenShots;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount()
  {
    if (freeToPlayGameScreenShots != null)
    {
      return freeToPlayGameScreenShots.size();
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
