package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.network.media.movie_network;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.CommentResponse;
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI
{
  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query the details of a chosen movie
   *
   * @param movie_id The movie id to locate the desired movie
   * @param apiKey   The api key required to authorize the API call
   * @return Retrofit call with the value of MovieResponse for the queried movie
   */
  @GET("movie/{movie_id}")
  Call<MovieResponse> getMovieById(@Path("movie_id") int movie_id,
      @Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of popular movies
   *
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of MovieResponse for the queried movies
   */
  @GET("movie/popular")
  Call<MovieResponse> getAllPopularMovies(@Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of top rated movies
   *
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of MovieResponse for the queried movies
   */
  @GET("movie/top_rated")
  Call<MovieResponse> getAllTopRatedMovies(@Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of movies which are playing now
   *
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of MovieResponse for the queried movies
   */
  @GET("movie/now_playing")
  Call<MovieResponse> getAllNowPlayingMovies(@Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of upcoming movies
   *
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of MovieResponse for the queried movies
   */
  @GET("movie/upcoming")
  Call<MovieResponse> getAllUpComingsMovies(@Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of latest movies
   *
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of MovieResponse for the queried movies
   */
  @GET("movie/latest")
  Call<MovieResponse> getAllLatestMovies(@Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of movie/movies based on the given parameter
   *
   * @param query  The keyword used to search for movies/movie that contain part/entire
   *               part of the query in it's title
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of MovieResponse for the queried movie/movies
   */
  @GET("search/movie")
  Call<MovieResponse> searchForMovie(@Query("api_key") String apiKey,
      @Query("query") String query);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query movie reviews based on the given parameter
   *
   * @param movie_id The movie id to locate the desired movie
   * @param apiKey   The api key required to authorize the API call
   * @return Retrofit call with the value of CommentResponse for the queried movie
   */
  @GET("movie/{movie_id}/reviews")
  Call<CommentResponse> getMovieReviews(@Path("movie_id") int movie_id,
      @Query("api_key") String apiKey);

  /**
   * Retrofit method that returns a response from TMDB webserver. Where the method
   * query details of similar movies to the selected movie
   *
   * @param apiKey The api key required to authorize the API call
   * @return Retrofit call with the value of MovieResponse for the queried movies
   */
  @GET("movie/{movie_id}/similar")
  Call<MovieResponse> getSimilarMovies(@Path("movie_id") int movie_id,
      @Query("api_key") String apiKey);
}
