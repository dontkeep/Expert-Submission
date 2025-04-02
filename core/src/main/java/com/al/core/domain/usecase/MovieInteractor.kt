package com.al.core.domain.usecase

import com.al.core.data.Resource
import com.al.core.domain.model.Movies
import com.al.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val moviesRepository: IMovieRepository): MovieUseCase {
    override fun getMovies(): Flow<Resource<List<Movies>>> {
        return moviesRepository.getMovies()
    }

    override fun getFavouriteMovies(): Flow<List<Movies>> {
        return moviesRepository.getFavouriteMovies()
    }

    override fun setFavouriteMovies(
        movies: Movies,
        state: Boolean
    ) {
        return moviesRepository.setFavouriteMovies(movies, state)
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<Movies>> {
        return moviesRepository.getDetailMovie(movieId)
    }
}