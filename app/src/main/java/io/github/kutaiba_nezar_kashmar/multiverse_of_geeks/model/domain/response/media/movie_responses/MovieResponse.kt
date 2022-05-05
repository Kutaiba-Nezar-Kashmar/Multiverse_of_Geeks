package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Movie
import java.util.ArrayList

class MovieResponse
{
    val id: Int = 0
    val title: String = ""
    val release_date: String = ""
    val overview: String = ""
    val vote_average: Float = 0.0F
    val poster_path: String = ""
    val results: List<Movie> = ArrayList()

    fun getMovie(): Movie
    {
        return Movie(
            id, title, release_date, overview, vote_average, poster_path
        )
    }
}