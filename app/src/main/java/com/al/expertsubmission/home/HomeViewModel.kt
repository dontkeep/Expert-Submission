package com.al.expertsubmission.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.al.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val movies = movieUseCase.getMovies().asLiveData()
}