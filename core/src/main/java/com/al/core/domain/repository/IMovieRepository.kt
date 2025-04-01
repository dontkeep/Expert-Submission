package com.al.core.domain.repository

import com.al.core.data.Resource
import com.al.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovies(): Flow<Resource<List<Movies>>>

    fun getFavouriteMovies(): Flow<List<Movies>>

    fun setFavouriteMovies(movies: Movies, state: Boolean)
}