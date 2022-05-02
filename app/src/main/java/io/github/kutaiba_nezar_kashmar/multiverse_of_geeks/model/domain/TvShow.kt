package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain

data class TvShow
    (
        var id: Int,
        var name: String,
        var first_air_date: String,
        var overview: String,
        var vote_average: Float,
        var poster_path: String,
)
{}