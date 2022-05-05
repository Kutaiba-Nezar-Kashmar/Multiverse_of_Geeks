package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.free_to_play

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.GameScreenShots

class FreeToPlayGameResponse
{
    val id : Int = 0
    val title : String = ""
    val thumbnail : String = ""
    val status : String = ""
    val short_description : String = ""
    val description : String = ""
    val game_url : String = ""
    val genre : String = ""
    val platform : String = ""
    val publisher : String = ""
    val developer : String = ""
    val release_date : String = ""
    val freetogame_profile_url : String = ""
    val minimum_system_requirements : FreeToPlayGameRequirementsResponse = FreeToPlayGameRequirementsResponse()
    val screenshots : List<GameScreenShots> = ArrayList()
}