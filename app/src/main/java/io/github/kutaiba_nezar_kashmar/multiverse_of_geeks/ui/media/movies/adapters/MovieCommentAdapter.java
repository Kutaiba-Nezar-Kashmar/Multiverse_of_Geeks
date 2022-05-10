package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.media.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieComment;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class MovieCommentAdapter extends FirebaseRecyclerAdapter<MovieComment, MovieCommentAdapter.CommentViewHolder>
{

  /**
   * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
   * {@link FirebaseRecyclerOptions} for configuration options.
   *
   * @param options
   */
  public MovieCommentAdapter(
      @NonNull FirebaseRecyclerOptions<MovieComment> options)
  {
    super(options);
  }

  @Override
  protected void onBindViewHolder(@NonNull CommentViewHolder holder,
      int position, @NonNull MovieComment model)
  {
    holder.commentBody.setText(model.getComment());
  }

  @NonNull
  @Override
  public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType)
  {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.movie_comment_item, parent, false);

    return new CommentViewHolder(view);
  }

  class CommentViewHolder extends RecyclerView.ViewHolder
  {
    private TextView commentBody;
    private Context context;
    public CommentViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();;
      commentBody = itemView.findViewById(R.id.comment_id);
    }
  }
}
