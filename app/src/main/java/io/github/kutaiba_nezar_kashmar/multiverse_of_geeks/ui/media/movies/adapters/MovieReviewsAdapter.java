package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Comment;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class MovieReviewsAdapter
    extends RecyclerView.Adapter<MovieReviewsAdapter.MovieCommentViewHolder>
{
  private List<Comment> comments;

  public MovieReviewsAdapter(List<Comment> comments)
  {
    this.comments = comments;
  }

  @NonNull
  @Override
  public MovieCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.movie_comment_item, parent, false);
    return new MovieCommentViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MovieCommentViewHolder holder,
      int position)
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

  //clear and reassign the cast list every time this method is called
  public void updateCommentList(final List<Comment> comments)
  {
    this.comments.clear();
    this.comments = comments;
    notifyDataSetChanged();
  }

  class MovieCommentViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView comment;
    private final TextView senderName;
    private final TextView timeStamp;
    private ImageView avatar;
    private Context context;

    public MovieCommentViewHolder(@NonNull View itemView)
    {
      super(itemView);
      comment = itemView.findViewById(R.id.comment_id);
      senderName = itemView.findViewById(R.id.sender_name_id);
      timeStamp = itemView.findViewById(R.id.comment_timestamp_id);
      context = itemView.getContext();
    }
  }
}
