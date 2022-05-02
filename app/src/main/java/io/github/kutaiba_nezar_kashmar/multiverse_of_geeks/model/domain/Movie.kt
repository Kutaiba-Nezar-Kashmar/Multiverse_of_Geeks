package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain

data class Movie
    (
        var id: Int,
        var title: String,
        var release_date: String,
        var overview: String,
        var vote_average: Float,
        var poster_path: String,
)
{}