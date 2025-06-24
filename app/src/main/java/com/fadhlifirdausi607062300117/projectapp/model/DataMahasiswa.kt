package com.fadhlifirdausi607062300117.projectapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mahasiswa")
data class DataMahasiswa(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0L,
    val nomor: String,
    val nama: String,
    val tanggalLahir: String ,
    val kelamin: String,
    val alamat: String
)

