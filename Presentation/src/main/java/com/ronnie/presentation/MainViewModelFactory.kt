package com.ronnie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ronnie.data2.RepositoryImpl

class MainViewModelFactory () : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(RepositoryImpl.getInstance()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}