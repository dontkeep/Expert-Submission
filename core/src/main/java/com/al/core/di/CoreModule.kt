package com.al.core.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.al.core.data.MovieRepository
import com.al.core.data.source.local.LocalDataSource
import com.al.core.data.source.local.room.MovieDatabase
import com.al.core.data.source.remote.RemoteDataSource
import com.al.core.data.source.remote.network.ApiService
import com.al.core.domain.repository.IMovieRepository
import com.al.core.utils.AppExecutors
import com.al.core.utils.AuthInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory {
        get<MovieDatabase>().movieDao()
    }

    single {
        Room.databaseBuilder(
            get(),
            MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}

val repositoryModules = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
}