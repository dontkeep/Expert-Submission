package com.al.expertsubmission.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.al.core.domain.model.Movies
import com.al.core.domain.usecase.MovieUseCase

class DetailViewModel(val movieUseCase: MovieUseCase): ViewModel() {
    private val _favoriteState = MutableLiveData<Boolean>()
    val favoriteState: LiveData<Boolean> = _favoriteState

    suspend fun toggleFavorite(movie: Movies) {
        val newStatus = !(_favoriteState.value ?: movie.isFavorite)
        movieUseCase.setFavouriteMovies(movie, newStatus)
        _favoriteState.postValue(newStatus)
    }

    fun setInitialFavoriteState(isFavorite: Boolean) {
        _favoriteState.value = isFavorite
    }
}