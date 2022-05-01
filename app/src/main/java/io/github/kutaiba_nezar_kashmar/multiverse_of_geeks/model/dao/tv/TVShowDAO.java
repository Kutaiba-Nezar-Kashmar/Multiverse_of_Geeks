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
  @Insert
  void insertTvShow(SingleTvShowResponse tv);

  @Query("SELECT * FROM tv")
  LiveData<List<SingleTvShowResponse>> getAllFavoriteTvShows();

  @Query("SELECT * FROM tv WHERE id = :id")
  LiveData<SingleTvShowResponse> getTvShowById(int id);

  @Delete
  void deleteTvShow(SingleTvShowResponse tv);
}
