package com.al.core.domain.usecase

import com.al.core.data.Resource
import com.al.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovies(): Flow<Resource<List<Movies>>>

    fun getFavouriteMovies(): Flow<List<Movies>>

    fun getDetailMovie(movieId: Int): Flow<Resource<Movies>>

    fun setFavouriteMovies(movies: Movies, state: Boolean)

}