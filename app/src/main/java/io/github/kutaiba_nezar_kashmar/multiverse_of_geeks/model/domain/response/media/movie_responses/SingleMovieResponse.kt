package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.movie_responses

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaGenreResponse
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaProductionCompaniesResponse
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaProductionCountriesResponse
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaSpokenLanguagesResponse
import java.util.ArrayList

@Entity(tableName = "movie")
data class SingleMovieResponse
    (
        var backdrop_path: String,
        var budget: Int,
        var homepage: String,
        @PrimaryKey var id: Int,
        var imdb_id: String,
        var original_language: String,
        var original_title: String,
        var overview: String,
        var popularity: Float,
        var poster_path: String,
        var revenue: Int,
        var runtime: Int,
        var status: String,
        var tagline: String,
        var title: String,
        var video: String,
        var vote_average: Float,
        var vote_count: Int,
)
{
    @Ignore
    var adult: Boolean = false

    @Ignore
    var belongs_to_collection: MovieCollectionResponse =
        MovieCollectionResponse()

    @Ignore
    val genres: List<MediaGenreResponse> = ArrayList()

    @Ignore
    var production_companies: List<MediaProductionCompaniesResponse> =
        ArrayList()

    @Ignore
    var release_date: String = ""

    @Ignore
    var production_countries: List<MediaProductionCountriesResponse> =
        ArrayList()

    @Ignore
    var spoken_languages: List<MediaSpokenLanguagesResponse> = ArrayList()
}
