package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.model.domain.local

data class Trending
    (
        var id: Int,
        var vote_count: Int,
        var original_language: String,
        var poster_path: String,
        var title: String,
        var vote_average: Float,
        var overview: String,
        var release_date: String,
        var popularity: Float,
        var media_type: String
)
{}