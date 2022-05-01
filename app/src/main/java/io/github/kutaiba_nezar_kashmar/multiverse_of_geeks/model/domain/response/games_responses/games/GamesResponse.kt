package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Game
import java.util.ArrayList

class GamesResponse
{
    val id: Int = 0
    val name: String = ""
    val released: String = ""
    val tba: Boolean = true
    val background_image: String = ""
    val rating: Float = 0.0F
    val playtime: Int = 0
    val updated: String = ""
    val platforms: ArrayList<PlatformsResponse> = ArrayList()
    val genres: ArrayList<GameGenreResponse> = ArrayList()
    val tags: ArrayList<GameTageResponse> = ArrayList()
    val esrb_rating: GameAgeRating = GameAgeRating()
    val short_screenshots: ArrayList<GameScreenShots> = ArrayList()
    val results: ArrayList<Game> = ArrayList()
    val developers: ArrayList<GamesDevelopersResponse> = ArrayList()

    fun getGame(): Game
    {
        return Game(
            id,
            name,
            released,
            tba,
            background_image,
            rating,
            playtime,
            updated,
            platforms,
            genres,
            tags,
            esrb_rating,
            short_screenshots,
            developers
        )
    }
}