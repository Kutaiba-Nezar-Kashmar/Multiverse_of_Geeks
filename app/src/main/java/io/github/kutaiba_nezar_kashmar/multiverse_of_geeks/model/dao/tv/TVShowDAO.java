package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.dao.tv;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses.SingleTvShowResponse;

@Dao public interface TVShowDAO
{
  /**
   * Create new entry for tv show to Database
   *
   * @param tv The tv show to be added
   */
  @Insert
  void insertTvShow(SingleTvShowResponse tv);

  /**
   * Query list of tv shows
   *
   * @return LiveData of a list of tv show object
   */
  @Query("SELECT * FROM tv")
  LiveData<List<SingleTvShowResponse>> getAllFavoriteTvShows();

  /**
   * Query a single tv show by given parameter
   *
   * @param id The desired tv show Id
   * @return LiveData of a tv show object
   */
  @Query("SELECT * FROM tv WHERE id = :id")
  LiveData<SingleTvShowResponse> getTvShowById(int id);

  /**
   * Delete a tv show object from database
   *
   * @param tv The game to be deleted
   */
  @Delete
  void deleteTvShow(SingleTvShowResponse tv);
}
