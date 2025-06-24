package com.fadhlifirdausi607062300117.projectapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhlifirdausi607062300117.projectapp.database.DataMahasiswaDao
import com.fadhlifirdausi607062300117.projectapp.model.DataMahasiswa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val dao: DataMahasiswaDao) : ViewModel() {

    private val _data = MutableStateFlow<DataMahasiswa?>(null)
    val data: StateFlow<DataMahasiswa?> = _data

    fun insert(nomor: String, nama: String, tanggalLahir: String, kelamin: String, alamat: String) {
        val mahasiswa = DataMahasiswa(
            nomor = nomor,
            nama = nama,
            tanggalLahir = tanggalLahir,
            kelamin= kelamin,
            alamat = alamat
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(mahasiswa)
        }
    }

    fun getMahasiswas(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val mahasiswa = dao.getMahasiswaById(id)
            _data.value = mahasiswa
        }
    }

    fun update(id: Long, nomor: String, nama: String, tanggalLahir: String, kelamin: String, alamat: String) {
        val mahasiswa = DataMahasiswa(
            id = id,
            nomor = nomor,
            nama = nama,
            tanggalLahir = tanggalLahir,
            kelamin= kelamin,
            alamat = alamat
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(mahasiswa)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}
