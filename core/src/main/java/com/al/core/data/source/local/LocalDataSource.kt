package com.al.core.data.source.local

import com.al.core.data.source.local.entity.MovieEntity
import com.al.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun setFavoriteMovies(movies: MovieEntity, newState: Boolean) {
        movies.isFavorite = newState
        movieDao.updateFavouriteMovie(movies)
    }
}