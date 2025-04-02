package com.al.core.data

import com.al.core.data.source.local.LocalDataSource
import com.al.core.data.source.remote.RemoteDataSource
import com.al.core.data.source.remote.network.ApiResponseResult
import com.al.core.data.source.remote.response.MovieResponse
import com.al.core.domain.model.Movies
import com.al.core.domain.repository.IMovieRepository
import com.al.core.utils.AppExecutors
import com.al.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors): IMovieRepository {
    override fun getMovies(): Flow<Resource<List<Movies>>> {
        return object: NetworkBoundResources<List<Movies>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponseResult<List<MovieResponse?>>> {
                return remoteDataSource.getMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResponse?>) {
                val moviesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(moviesList)
            }
        }.asFlow()
    }

    override fun getFavouriteMovies(): Flow<List<Movies>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<Movies>> {
        return remoteDataSource.getDetailMovie(movieId).map {
            when (it) {
                is ApiResponseResult.Success -> {
                    val movie = it.data?.let { it1 -> DataMapper.mapResponseToDomain(it1) }
                    Resource.Success(movie as Movies)
                }
                is ApiResponseResult.Empty -> Resource.Error("Empty")
                is ApiResponseResult.Error -> Resource.Error(it.errorMessage)
            }
        }
    }

    override fun setFavouriteMovies(
        movies: Movies,
        state: Boolean
    ) {
        val moviesEntity = DataMapper.mapDomainToEntity(movies)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(moviesEntity, state) }
    }
}