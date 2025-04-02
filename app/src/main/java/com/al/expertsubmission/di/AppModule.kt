package com.al.expertsubmission.di

import android.content.Context
import com.al.core.domain.usecase.MovieInteractor
import com.al.core.domain.usecase.MovieUseCase
import com.al.expertsubmission.detail.DetailViewModel
import com.al.expertsubmission.home.HomeViewModel
import com.al.expertsubmission.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SettingsViewModel(androidContext().getSharedPreferences("settings", Context.MODE_PRIVATE)) }

}

