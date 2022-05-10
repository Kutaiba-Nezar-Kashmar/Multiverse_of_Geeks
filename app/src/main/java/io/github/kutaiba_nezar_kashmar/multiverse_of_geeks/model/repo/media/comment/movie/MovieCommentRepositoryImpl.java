package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.movie.MovieComment;

public class MovieCommentRepositoryImpl implements MovieCommentRepository
{
  private static MovieCommentRepository instance;
  private final FirebaseDatabase firebaseDatabase;
  private DatabaseReference reference;
  private final MutableLiveData<MovieComment> myComments;
  private final MutableLiveData<List<MovieComment>> movieComments;

  private MovieCommentRepositoryImpl()
  {
    firebaseDatabase = FirebaseDatabase.getInstance();
    reference = firebaseDatabase.getReference().child("movieComment");
    myComments = new MutableLiveData<>();
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
    reference.child("users").child(userId)
        .child(String.valueOf(movieComment.getMovieId())).push()
        .setValue(movieComment);
  }

  @Override
  public void deleteComment(MovieComment movieComment)
  {

  }

  @Override
  public Query getMovieComments(int movieId)
  {
    return reference.child("users");
  }
}
