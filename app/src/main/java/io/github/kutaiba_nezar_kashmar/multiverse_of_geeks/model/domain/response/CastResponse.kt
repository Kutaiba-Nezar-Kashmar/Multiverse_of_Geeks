package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Cast
import java.util.ArrayList

class CastResponse
{
    val id: Int = 0
    val gender: Int = 0
    val known_for_department: String = ""
    val name: String = ""
    val profile_path: String = ""
    val character: String = ""
    val cast: List<Cast> = ArrayList()

    fun getCast(): Cast
    {
        return Cast(
            id, gender, known_for_department, name, profile_path, character
        )
    }

    fun getCastList() : List<Cast>
    {
        return cast
    }
}
