package com.al.core.data

import com.al.core.data.source.local.LocalDataSource
import com.al.core.data.source.remote.RemoteDataSource
import com.al.core.domain.model.Movies
import com.al.core.domain.repository.IMovieRepository
import com.al.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors): IMovieRepository {
    override fun getMovies(): Flow<Resource<List<Movies>>> {
        TODO("Not yet implemented")
    }

    override fun getFavouriteMovies(): Flow<List<Movies>> {
        TODO("Not yet implemented")
    }

    override fun setFavouriteMovies(
        movies: Movies,
        state: Boolean
    ) {
        TODO("Not yet implemented")
    }
}