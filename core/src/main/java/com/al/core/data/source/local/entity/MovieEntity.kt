package com.al.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
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
    var isFavorite: Boolean = false
)