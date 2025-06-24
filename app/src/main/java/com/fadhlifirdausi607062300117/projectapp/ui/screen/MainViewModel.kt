package com.fadhlifirdausi607062300117.projectapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhlifirdausi607062300117.projectapp.database.DataMahasiswaDao
import com.fadhlifirdausi607062300117.projectapp.model.DataMahasiswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: DataMahasiswaDao): ViewModel() {

    val data: StateFlow<List<DataMahasiswa>> = dao.getMahasiswa().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

}