package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieComment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.util.GlideApp;

public class MovieCommentAdapter
    extends RecyclerView.Adapter<MovieCommentAdapter.CommentViewHolder>
{
  private List<MovieComment> comments;

  public MovieCommentAdapter(List<MovieComment> comments)
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
    holder.username.setText(comments.get(position).getUsername());
    holder.commentBody.setText(comments.get(position).getComment());
    holder.timestamp.setText(comments.get(position).getTimeStamp());

    //Glide to set image to userImage using storage reference
    GlideApp.with(holder.context).load(comments.get(position).getUserImage())
        .placeholder(R.drawable.avatar_placeholder)
        .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true)
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

  public void updateMovieCommentList(final List<MovieComment> comments)
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
