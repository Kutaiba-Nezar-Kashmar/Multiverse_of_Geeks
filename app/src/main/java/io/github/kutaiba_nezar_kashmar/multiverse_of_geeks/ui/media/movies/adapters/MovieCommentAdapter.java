package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.R;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieComment;

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

  public void updateMovieCommentList(final List<MovieComment> comments)
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
