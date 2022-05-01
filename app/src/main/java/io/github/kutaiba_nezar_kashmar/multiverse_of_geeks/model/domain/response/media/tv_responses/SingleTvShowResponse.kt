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
class SingleTvShowResponse
{
    @Ignore
    val adult : Boolean = true
    var backdrop_path : String = ""
        set(value)
        {
            field = value
        }
    @Ignore
    val created_by : ArrayList<TvShowCreatorResponse> = ArrayList()
    @Ignore
    val episode_run_time : ArrayList<Integer> = ArrayList()
    var first_air_date : String = ""
        set(value)
        {
            field = value
        }
    @Ignore
    val genres : ArrayList<MediaGenreResponse> = ArrayList()
    var homepage : String = ""
        set(value)
        {
            field = value
        }
    @PrimaryKey
    var id : Int = 0
        set(value)
        {
            field = value
        }
    var in_production : Boolean = true
        set(value)
        {
            field = value
        }
    @Ignore
    val languages : ArrayList<String> = ArrayList()
    var last_air_date : String = ""
        set(value)
        {
            field = value
        }
    @Ignore
    val last_episode_to_air : TvShowLastEpisodeToAirResponse = TvShowLastEpisodeToAirResponse()
    var name : String = ""
        set(value)
        {
            field = value
        }
    @Ignore
    val networks : ArrayList<TvShowNetworkResponse> = ArrayList()
    var number_of_episodes : Int = 0
        set(value)
        {
            field = value
        }
    var number_of_seasons : Int = 0
        set(value)
        {
            field = value
        }
    @Ignore
    val origin_country : ArrayList<String> = ArrayList()
    var original_language : String = ""
        set(value)
        {
            field = value
        }
    var original_name : String = ""
        set(value)
        {
            field = value
        }
    var overview : String = ""
        set(value)
        {
            field = value
        }
    var popularity : Float = 0.0F
        set(value)
        {
            field = value
        }
    var poster_path : String = ""
        set(value)
        {
            field = value
        }
    @Ignore
    val production_companies : ArrayList<MediaProductionCompaniesResponse> = ArrayList()
    @Ignore
    val production_countries : ArrayList<MediaProductionCountriesResponse> = ArrayList()
    @Ignore
    val seasons : ArrayList<TvShowSeasonResponse> = ArrayList()
    @Ignore
    val spoken_languages : ArrayList<MediaSpokenLanguagesResponse> = ArrayList()
    var status : String = ""
        set(value)
        {
            field = value
        }
    var tagline : String = ""
        set(value)
        {
            field = value
        }
    var type : String = ""
        set(value)
        {
            field = value
        }
    var vote_average : Float = 0.0F
        set(value)
        {
            field = value
        }
    var vote_count: Float = 0.0F
        set(value)
        {
            field = value
        }
}