package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.repo.comment_repo;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDateTime;
import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.Comment;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.User;

public class CommentRepository
{
  private static CommentRepository instance;
  private MutableLiveData<ArrayList<Comment>> comments = new MutableLiveData<>();

  private CommentRepository()
  {
    ArrayList<Comment> commentArrayList = new ArrayList<>();
    commentArrayList.add(new Comment("hello1", "1", new User("user1")));
    commentArrayList.add(new Comment("hello2", "1", new User("user11")));
    commentArrayList.add(new Comment("hello3", "1", new User("user111")));
    commentArrayList.add(new Comment("hello4", "1", new User("user1111")));
    commentArrayList.add(new Comment("hello5", "1", new User("user11111")));
    commentArrayList.add(new Comment("hello6", "1", new User("user111111")));
    commentArrayList.add(new Comment("hello7", "1", new User("user1111111")));
    commentArrayList.add(new Comment("hello8", "1", new User("user11111111")));
    commentArrayList.add(new Comment("hello9", "1", new User("user111111111")));
    commentArrayList.add(new Comment("hello0", "1", new User("user1111111111")));
    comments.setValue(commentArrayList);
  }

  public static synchronized CommentRepository getInstance()
  {
    if (instance == null)
    {
      instance = new CommentRepository();
    }
    return instance;
  }

  public LiveData<ArrayList<Comment>> getComments()
  {
    return comments;
  }
}
