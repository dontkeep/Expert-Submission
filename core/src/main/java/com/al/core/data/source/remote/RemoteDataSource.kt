package com.al.core.data.source.remote

import android.util.Log
import co.touchlab.stately.concurrency.synchronize
import com.al.core.data.source.local.room.MovieDao
import com.al.core.data.source.remote.network.ApiResponseResult
import com.al.core.data.source.remote.network.ApiService
import com.al.core.data.source.remote.response.MovieDetailResponse
import com.al.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getMovies(): Flow<ApiResponseResult<List<MovieResponse?>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.results
                if(dataArray?.isNotEmpty() == true) {
                    emit(ApiResponseResult.Success(response.results))
                } else {
                    emit(ApiResponseResult.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponseResult.Error(e.toString()))
                Log.e("Error message", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailMovie(movieId: Int): Flow<ApiResponseResult<MovieDetailResponse?>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(movieId)
                emit(ApiResponseResult.Success(response))
            } catch (e: Exception) {
                emit(ApiResponseResult.Error(e.toString()))
                Log.e("Error message", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}