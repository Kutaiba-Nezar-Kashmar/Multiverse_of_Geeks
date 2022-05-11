package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.tv;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.tv.TvComment;

public class TvCommentRepositoryImpl implements TvCommentRepository
{
  private static TvCommentRepository instance;
  private final FirebaseDatabase firebaseDatabase;
  private DatabaseReference reference;
  private final MutableLiveData<TvComment> myTvComment;
  private final MutableLiveData<List<TvComment>> tvComments;

  private TvCommentRepositoryImpl()
  {
    firebaseDatabase = FirebaseDatabase.getInstance();
    reference = firebaseDatabase.getReference().child("tvComment")
        .child("users");
    myTvComment = new MutableLiveData<>();
    tvComments = new MutableLiveData<>();
  }

  public static synchronized TvCommentRepository getInstance()
  {
    if (instance == null)
    {
      instance = new TvCommentRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void postComment(TvComment tvComment, String userId)
  {
    reference.child(userId).child(String.valueOf(tvComment.getTvId())).push()
        .setValue(tvComment);
  }

  @Override
  public void deleteComment(TvComment tvComment)
  {

  }

  @Override
  public LiveData<List<TvComment>> tvComments(int tvId)
  {
    List<TvComment> commentList = new ArrayList<>();
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
              TvComment tvComment = dss.getValue(TvComment.class);
              if (tvComment != null)
              {
                if (tvComment.getTvId() == tvId)
                {
                  commentList.add(tvComment);
                }
              }
            }
          }
        }
        tvComments.postValue(commentList);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error)
      {

      }
    };
    reference.addListenerForSingleValueEvent(listener);
    return tvComments;
  }
}
