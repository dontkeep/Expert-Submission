package com.al.expertsubmission.settings

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel(private val sharedPreferences: SharedPreferences): ViewModel() {
    private val _isDarkMode = MutableLiveData<Boolean>()
    val isDarkMode: LiveData<Boolean> get() = _isDarkMode

    init {
        _isDarkMode.value = sharedPreferences.getBoolean("theme", false)
    }

    fun setDarkMode(isChecked: Boolean) {
        _isDarkMode.value = isChecked
        sharedPreferences.edit().putBoolean("theme", isChecked).apply()
    }
}