package com.al.core.utils

import com.al.core.data.source.local.entity.MovieEntity
import com.al.core.data.source.remote.response.MovieDetailResponse
import com.al.core.data.source.remote.response.MovieResponse
import com.al.core.domain.model.Movies

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse?>): List<MovieEntity> {
        return input.map {
            MovieEntity(
                id = it?.id ?: 0,
                title = it?.title.orEmpty(),
                overview = it?.overview,
                originalLanguage = it?.originalLanguage,
                originalTitle = it?.originalTitle,
                posterPath = it?.posterPath,
                backdropPath = it?.backdropPath,
                releaseDate = it?.releaseDate,
                popularity = (it?.popularity as? Double) ?: 0.0,
                voteAverage = (it?.voteAverage as? Double) ?: 0.0,
                adult = it?.adult ?: false,
                voteCount = it?.voteCount,
                isFavorite = false
            )
        }
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movies> {
        return input.map {
            Movies(
                id = it.id,
                title = it.title,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                adult = it.adult,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }
    }

    fun mapDomainToEntity(input: Movies): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            adult = input.adult,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )
    }

    fun mapResponseToDomain(input: MovieDetailResponse): Movies {
        return Movies(
            id = input.id ?: 0,
            title = input.title.orEmpty(),
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            popularity = input.popularity as? Double ?: 0.0,
            voteAverage = input.voteAverage as? Double ?: 0.0,
            adult = input.adult,
            voteCount = input.voteCount,
            isFavorite = false
        )
    }
}
