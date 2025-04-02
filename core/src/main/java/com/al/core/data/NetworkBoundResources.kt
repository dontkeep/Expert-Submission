package com.al.core.data

import com.al.core.data.source.remote.network.ApiResponseResult
import com.al.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResources<ResultType>{
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponseResult.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
                is ApiResponseResult.Empty -> {
                    emitAll(loadFromDB().map {
                        Resource.Success(it)
                    })
                }
                is ApiResponseResult.Error -> {
                    onFetchFailed()
                    emit(
                        Resource.Error<ResultType>(apiResponse.errorMessage)
                    )
                }
            }
        } else {
            emitAll(loadFromDB().map {
                Resource.Success(it)
            })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponseResult<List<MovieResponse?>>>

    protected abstract suspend fun saveCallResult(data: List<MovieResponse?>)

    fun asFlow(): Flow<Resource<ResultType>> = result
}