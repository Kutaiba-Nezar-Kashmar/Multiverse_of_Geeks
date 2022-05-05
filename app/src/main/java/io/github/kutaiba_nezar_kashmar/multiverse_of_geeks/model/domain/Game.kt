package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response.games_responses.games.*
import java.util.ArrayList

@Entity(tableName = "game")
data class Game(
        @PrimaryKey var id: Int = 0,
        var name: String = "",
        var released: String = "",
        var tba: Boolean = true,
        var background_image: String = "",
        var rating: Float = 0.0F,
        var playtime: Int = 0,
        var updated: String = "",

        @Ignore val platforms: List<PlatformsResponse> = ArrayList(),

        @Ignore val genres: List<GameGenreResponse> = ArrayList(),

        @Ignore val tags: List<GameTageResponse> = ArrayList(),

        @Ignore val esrb_rating: GameAgeRating = GameAgeRating(),

        @Ignore val short_screenshots: List<GameScreenShots> = ArrayList(),

        @Ignore val developers: List<GamesDevelopersResponse> = ArrayList()
)
{

}