package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.comment.game;

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

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.game.GameComment;

public class GameCommentRepositoryImpl implements GameCommentRepository
{
  private static GameCommentRepository instance;
  private final DatabaseReference reference;
  private final MutableLiveData<List<GameComment>> gameComments;

  private GameCommentRepositoryImpl()
  {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    reference = firebaseDatabase.getReference().child("gameComment")
        .child("users");
    gameComments = new MutableLiveData<>();
  }

  public static synchronized GameCommentRepository getInstance()
  {
    if (instance == null)
    {
      instance = new GameCommentRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void postComment(GameComment gameComment, String userId)
  {
    reference.child(userId).child(String.valueOf(gameComment.getGameId()))
        .push().setValue(gameComment);
  }

  @Override
  public LiveData<List<GameComment>> gameComments(int gameId)
  {
    List<GameComment> commentList = new ArrayList<>();
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
              GameComment gameComment = dss.getValue(GameComment.class);
              if (gameComment != null)
              {
                if (gameComment.getGameId() == gameId)
                {
                  commentList.add(gameComment);
                }
              }
            }
          }
        }
        gameComments.setValue(commentList);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error)
      {

      }
    };
    reference.addValueEventListener(listener);
    return gameComments;
  }
}
