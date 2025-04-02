package com.al.core.data.source.remote.network

import com.al.core.data.source.remote.response.MovieListResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovies(): MovieListResponse
}