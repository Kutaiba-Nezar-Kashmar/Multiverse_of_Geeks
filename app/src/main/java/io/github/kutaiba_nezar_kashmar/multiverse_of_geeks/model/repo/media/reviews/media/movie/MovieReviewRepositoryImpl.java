package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.repo.media.reviews.media.movie;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie.MovieReview;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.firebase.media.movie.MovieReviewLiveData;

public class MovieReviewRepositoryImpl implements MovieReviewRepository
{
  private static MovieReviewRepositoryImpl instance;
  private final FirebaseDatabase firebaseDatabase;
  private DatabaseReference reference;
  private MovieReviewLiveData review;

  private MovieReviewRepositoryImpl()
  {
    firebaseDatabase = FirebaseDatabase.getInstance("https://multiverse-of-geeks-default-rtdb.europe-west1.firebasedatabase.app");
  }

  public static synchronized MovieReviewRepositoryImpl getInstance()
  {
    if (instance == null)
    {
      instance = new MovieReviewRepositoryImpl();
    }
    return instance;
  }

  @Override
  public void init(String userId)
  {
    reference = FirebaseDatabase.getInstance().getReference().child("users")
        .child(userId);
    review = new MovieReviewLiveData(reference);
  }

  @Override
  public void postReview(MovieReview movieReview)
  {
    reference.push().setValue(movieReview);
  }

  @Override
  public MovieReviewLiveData getReviews()
  {
    return review;
  }
}
