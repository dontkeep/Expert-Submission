package com.al.core.domain.model

data class Movies(
    val id: Int,
    val title: String,
    val overview: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val popularity: Double?,
    val voteAverage: Double?,
    val adult: Boolean?,
    val voteCount: Int?,
    val isFavorite: Boolean = false
)