package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain

class Comment
    (
        val id: String = "",
        val author: String = "",
        val author_details: Commenter? = null,
        val content: String? = "",
        val created_at: String? = "",
)
{}