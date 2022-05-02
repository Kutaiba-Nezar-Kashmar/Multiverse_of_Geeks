package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaGenreResponse
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaProductionCompaniesResponse
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaProductionCountriesResponse
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.MediaSpokenLanguagesResponse
import java.util.ArrayList

@Entity(tableName = "tv")
data class SingleTvShowResponse
    (
        var backdrop_path: String,
        var first_air_date: String,
        var homepage: String,
        @PrimaryKey var id: Int,
        var in_production: Boolean,
        var last_air_date: String,
        var name: String,
        var number_of_episodes: Int,
        var number_of_seasons: Int,
        var original_language: String,
        var original_name: String,
        var overview: String,
        var popularity: Float,
        var poster_path: String,
        var status: String,
        var tagline: String,
        var type: String,
        var vote_average: Float,
        var vote_count: Float,
)
{
    @Ignore
    val adult: Boolean = false

    @Ignore
    val created_by: ArrayList<TvShowCreatorResponse> = ArrayList()

    @Ignore
    val episode_run_time: ArrayList<Integer> = ArrayList()

    @Ignore
    val genres: ArrayList<MediaGenreResponse> = ArrayList()

    @Ignore
    val languages: ArrayList<String> = ArrayList()

    @Ignore
    val last_episode_to_air: TvShowLastEpisodeToAirResponse =
        TvShowLastEpisodeToAirResponse()

    @Ignore
    val networks: ArrayList<TvShowNetworkResponse> = ArrayList()

    @Ignore
    val origin_country: ArrayList<String> = ArrayList()

    @Ignore
    val production_companies: ArrayList<MediaProductionCompaniesResponse> =
        ArrayList()

    @Ignore
    val production_countries: ArrayList<MediaProductionCountriesResponse> =
        ArrayList()

    @Ignore
    val seasons: ArrayList<TvShowSeasonResponse> = ArrayList()

    @Ignore
    val spoken_languages: ArrayList<MediaSpokenLanguagesResponse> = ArrayList()
}