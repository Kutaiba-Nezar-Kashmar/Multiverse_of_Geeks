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
class SingleMovieResponse
{
    @Ignore
    val adult: Boolean = true

    @Ignore
    val belongs_to_collection: MovieCollectionResponse =
        MovieCollectionResponse()
    var backdrop_path: String = ""
        set(value)
        {
            field = value
        }
    var budget: Int = 0
        set(value)
        {
            field = value
        }

    @Ignore
    val genres: ArrayList<MediaGenreResponse> = ArrayList()
    var homepage: String = ""
        set(value)
        {
            field = value
        }

    @PrimaryKey
    var id: Int = 0
        set(value)
        {
            field = value
        }
    var imdb_id: String = ""
        set(value)
        {
            field = value
        }
    var original_language: String = ""
        set(value)
        {
            field = value
        }
    var original_title: String = ""
        set(value)
        {
            field = value
        }
    var overview: String = ""
        set(value)
        {
            field = value
        }
    var popularity: Float = 0.0F
        set(value)
        {
            field = value
        }
    var poster_path: String = ""
        set(value)
        {
            field = value
        }

    @Ignore
    val production_companies: ArrayList<MediaProductionCompaniesResponse> =
        ArrayList()

    @Ignore
    val release_date: String = ""
    var revenue: Int = 0
        set(value)
        {
            field = value
        }
    var runtime: Int = 0
        set(value)
        {
            field = value
        }

    @Ignore
    val production_countries: ArrayList<MediaProductionCountriesResponse> =
        ArrayList()

    @Ignore
    val spoken_languages: ArrayList<MediaSpokenLanguagesResponse> = ArrayList()
    var status: String = ""
        set(value)
        {
            field = value
        }
    var tagline: String = ""
        set(value)
        {
            field = value
        }
    var title: String = ""
        set(value)
        {
            field = value
        }
    var video: String = ""
        set(value)
        {
            field = value
        }
    var vote_average: Float = 0.0F
        set(value)
        {
            field = value
        }
    var vote_count: Int = 0
        set(value)
        {
            field = value
        }
}
