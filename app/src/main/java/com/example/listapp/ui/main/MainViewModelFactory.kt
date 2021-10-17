package com.example.listapp.ui.main

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listapp.ui.main.ui.MainViewModel

class MainViewModelFactory(private val sharedPreferences : SharedPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(sharedPreferences) as T
    }
}