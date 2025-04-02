package com.al.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.al.core.domain.usecase.MovieUseCase

class FavouriteViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val favMovies = movieUseCase.getFavouriteMovies().asLiveData()
}