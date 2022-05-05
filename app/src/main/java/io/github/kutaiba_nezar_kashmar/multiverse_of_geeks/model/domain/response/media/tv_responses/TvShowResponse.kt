package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.media.tv_responses

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.TvShow
import java.util.ArrayList

class TvShowResponse
{
    val id : Int = 0
    val name : String = ""
    val first_air_date : String = ""
    val overview : String = ""
    val vote_average : Float = 0.0F
    val poster_path : String = ""
    val results : List<TvShow> = ArrayList()

    fun getTvShow() : TvShow
    {
        return TvShow(id, name, first_air_date, overview, vote_average,
            poster_path)
    }
}