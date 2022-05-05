package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Trending
import java.util.ArrayList

class TrendingResponse
{
    val id: Int = 0
    val vote_count: Int = 0
    val original_language: String = ""
    val poster_path: String = ""
    val title: String = ""
    val vote_average: Float = 0.0F
    val overview: String = ""
    val release_date: String = ""
    val popularity: Float = 0.0F
    val media_type: String = ""
    val results: List<Trending> = ArrayList()

    fun getTrending(): Trending
    {
        return Trending(
            id,
            vote_count,
            original_language,
            poster_path,
            title,
            vote_average,
            overview,
            release_date,
            popularity,
            media_type
        )
    }
}