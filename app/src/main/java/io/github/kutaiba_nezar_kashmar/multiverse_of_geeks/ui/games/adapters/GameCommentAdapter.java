package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.games.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameComment;

public class GameCommentAdapter extends RecyclerView.Adapter<GameCommentAdapter.CommentViewHolder>
{
  private List<GameComment> comments;

  public GameCommentAdapter(List<GameComment> comments)
  {
    this.comments = comments;
  }

  @NonNull
  @Override
  public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.comment_item, parent, false);
    return new GameCommentAdapter.CommentViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CommentViewHolder holder, int position)
  {
    holder.commentBody.setText(comments.get(position).getComment());
  }

  @Override
  public int getItemCount()
  {
    if (comments != null)
    {
      return comments.size();
    }
    return 0;
  }

  public void updateGameCommentList(final List<GameComment> comments)
  {
    this.comments.clear();
    this.comments = comments;
    notifyDataSetChanged();
  }

  class CommentViewHolder extends RecyclerView.ViewHolder
  {
    private TextView commentBody;
    private Context context;

    public CommentViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      commentBody = itemView.findViewById(R.id.comment_id);
    }
  }
}
