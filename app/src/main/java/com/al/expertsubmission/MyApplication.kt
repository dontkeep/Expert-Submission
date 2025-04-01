package com.al.expertsubmission

import android.app.Application
import com.al.core.di.databaseModule
import com.al.core.di.networkModule
import com.al.core.di.repositoryModules
import com.al.expertsubmission.di.useCaseModule
import com.al.expertsubmission.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(listOf(
                databaseModule,
                networkModule,
                repositoryModules,
                useCaseModule,
                viewModelModule
            ))
        }
    }
}