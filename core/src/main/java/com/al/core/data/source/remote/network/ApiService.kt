package com.al.core.data.source.remote.network

import com.al.core.data.source.remote.response.MovieListResponse
import com.al.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovies(): MovieListResponse
}