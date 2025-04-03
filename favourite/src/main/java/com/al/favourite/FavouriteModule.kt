package com.al.favourite

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {
    viewModel {
        FavouriteViewModel(get())
    }
}