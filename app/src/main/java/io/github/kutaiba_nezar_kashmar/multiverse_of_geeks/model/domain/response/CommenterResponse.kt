package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local.Commenter

class CommenterResponse
{
    val avatar_path : String  = ""

    fun getCommenter() : Commenter
    {
        return Commenter(avatar_path)
    }
}