package com.fadhlifirdausi607062300117.projectapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fadhlifirdausi607062300117.projectapp.database.DataMahasiswaDao
import com.fadhlifirdausi607062300117.projectapp.ui.screen.DetailViewModel
import com.fadhlifirdausi607062300117.projectapp.ui.screen.MainViewModel

class ViewModelFactory (
    private val dao: DataMahasiswaDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel:: class.java)) {
            return MainViewModel(dao) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}