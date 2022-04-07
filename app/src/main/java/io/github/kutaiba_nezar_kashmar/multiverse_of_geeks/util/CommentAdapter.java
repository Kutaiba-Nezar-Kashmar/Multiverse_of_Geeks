package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Comment;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>
{
  private ArrayList<Comment> comments;

  public CommentAdapter(ArrayList<Comment> comments)
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
    return new CommentViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CommentViewHolder holder, int position)
  {
    holder.comment.setText(comments.get(position).getContent());
    holder.senderName.setText(comments.get(position).getAuthor());
    holder.timeStamp.setText(comments.get(position).getCreated_at());
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

  public void updateCommentList(final ArrayList<Comment> comments)
  {
    this.comments.clear();
    this.comments = comments;
    notifyDataSetChanged();
  }

  class CommentViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView comment;
    private final TextView senderName;
    private final TextView timeStamp;
    private ImageView avatar;
    private Context context;

    public CommentViewHolder(@NonNull View itemView)
    {
      super(itemView);
      comment = itemView.findViewById(R.id.comment_id);
      senderName = itemView.findViewById(R.id.sender_name_id);
      timeStamp = itemView.findViewById(R.id.comment_timestamp_id);
      context = itemView.getContext();
    }
  }
}
