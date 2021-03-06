package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieComment;

public class MovieCommentRepositoryImpl implements MovieCommentRepository
{
  private static MovieCommentRepository instance;
  private final DatabaseReference reference;
  private final MutableLiveData<List<MovieComment>> movieComments;

  private MovieCommentRepositoryImpl()
  {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    reference = firebaseDatabase.getReference().child("movieComment")
        .child("users");
    movieComments = new MutableLiveData<>();
  }

  public static synchronized MovieCommentRepository getInstance()
  {
    if (instance == null)
    {
      instance = new MovieCommentRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void postComment(MovieComment movieComment, String userId)
  {
    reference.child(userId).child(String.valueOf(movieComment.getMovieId()))
        .push().setValue(movieComment);
  }

  @Override
  public LiveData<List<MovieComment>> movieComments(int movieId)
  {
    List<MovieComment> commentList = new ArrayList<>();
    ValueEventListener listener = new ValueEventListener()
    {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot)
      {
        for (DataSnapshot d : snapshot.getChildren())
        {
          for (DataSnapshot ds : d.getChildren())
          {
            for (DataSnapshot dss : ds.getChildren())
            {
              MovieComment movieComment = dss.getValue(MovieComment.class);
              if (movieComment != null)
              {
                if (movieComment.getMovieId() == movieId)
                {
                  commentList.add(movieComment);
                }
              }
            }
          }
        }
        movieComments.setValue(commentList);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error)
      {

      }
    };
    reference.addValueEventListener(listener);
    return movieComments;
  }
}
