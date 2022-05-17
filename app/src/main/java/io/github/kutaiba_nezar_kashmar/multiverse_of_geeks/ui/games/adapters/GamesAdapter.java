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
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Game;

public class GamesAdapter
    extends RecyclerView.Adapter<GamesAdapter.GamesViewHolder>
{
  private List<Game> gamesResponses;
  private OnClickListener listener;

  public GamesAdapter(List<Game> gamesResponses)
  {
    this.gamesResponses = gamesResponses;
  }

  @NonNull
  @Override
  public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.games_item, parent, false);
    return new GamesViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull GamesViewHolder holder, int position)
  {
    //Glide to set image to gamePoster
    Glide.with(holder.context)
        .load(gamesResponses.get(position).getBackground_image())
        .into(holder.gamePoster);

    holder.gameTitle.setText(gamesResponses.get(position).getName());
  }

  public void updateGameList(final List<Game> gamesResponses)
  {
    this.gamesResponses.clear();
    this.gamesResponses = gamesResponses;
    notifyDataSetChanged();
  }

  public void setListener(OnClickListener listener)
  {
    this.listener = listener;
  }

  @Override
  public int getItemCount()
  {
    if (gamesResponses != null)
    {
      return gamesResponses.size();
    }
    return 0;
  }

  class GamesViewHolder extends RecyclerView.ViewHolder
  {
    private final ImageView gamePoster;
    private final TextView gameTitle;
    private final Context context;

    public GamesViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      gamePoster = itemView.findViewById(R.id.game_poster);
      gameTitle = itemView.findViewById(R.id.game_name);

      itemView.setOnClickListener(view -> {
        listener.onClick(gamesResponses.get(getBindingAdapterPosition()));
      });
    }
  }

  public interface OnClickListener
  {
    void onClick(Game game);
  }
}
