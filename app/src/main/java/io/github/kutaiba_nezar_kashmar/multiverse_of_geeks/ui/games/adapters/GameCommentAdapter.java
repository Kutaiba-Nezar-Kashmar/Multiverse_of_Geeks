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
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameComment;

public class GameCommentAdapter
    extends RecyclerView.Adapter<GameCommentAdapter.CommentViewHolder>
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
    holder.username.setText(comments.get(position).getUsername());
    holder.commentBody.setText(comments.get(position).getComment());
    holder.timestamp.setText(comments.get(position).getTimeStamp());
    Glide.with(holder.context).load(comments.get(position).getUserImage())
        .into(holder.userImage);
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
    private final TextView commentBody;
    private final TextView username;
    private final TextView timestamp;
    private final ImageView userImage;
    private final Context context;

    public CommentViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      commentBody = itemView.findViewById(R.id.comment_id);
      timestamp = itemView.findViewById(R.id.comment_timestamp_id);
      username = itemView.findViewById(R.id.sender_name_id);
      userImage = itemView.findViewById(R.id.commenter_avatar);
    }
  }
}
