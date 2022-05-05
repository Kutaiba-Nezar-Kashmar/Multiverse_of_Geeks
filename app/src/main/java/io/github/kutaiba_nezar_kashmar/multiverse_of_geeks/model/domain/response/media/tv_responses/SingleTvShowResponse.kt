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
    val created_by: List<TvShowCreatorResponse> = ArrayList()

    @Ignore
    val episode_run_time: List<Integer> = ArrayList()

    @Ignore
    val genres: List<MediaGenreResponse> = ArrayList()

    @Ignore
    val languages: List<String> = ArrayList()

    @Ignore
    val last_episode_to_air: TvShowLastEpisodeToAirResponse =
        TvShowLastEpisodeToAirResponse()

    @Ignore
    val networks: List<TvShowNetworkResponse> = ArrayList()

    @Ignore
    val origin_country: List<String> = ArrayList()

    @Ignore
    val production_companies: List<MediaProductionCompaniesResponse> =
        ArrayList()

    @Ignore
    val production_countries: List<MediaProductionCountriesResponse> =
        ArrayList()

    @Ignore
    val seasons: List<TvShowSeasonResponse> = ArrayList()

    @Ignore
    val spoken_languages: List<MediaSpokenLanguagesResponse> = ArrayList()
}