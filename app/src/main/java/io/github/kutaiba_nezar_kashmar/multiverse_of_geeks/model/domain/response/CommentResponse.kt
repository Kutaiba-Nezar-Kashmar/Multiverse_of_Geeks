package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.response

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Comment
import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.Commenter
import java.util.ArrayList

class CommentResponse
{
    val id : String = ""
    val author : String = ""
    val author_details : Commenter = Commenter("")
    val content : String  = ""
    val created_at : String  = ""
    val results : List<Comment> = ArrayList()

    fun getComment() : Comment
    {
        return Comment(id, author, author_details, content, created_at)
    }
}