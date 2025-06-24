package com.fadhlifirdausi607062300117.projectapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fadhlifirdausi607062300117.projectapp.model.DataMahasiswa
import kotlinx.coroutines.flow.Flow

@Dao
interface DataMahasiswaDao {
    @Insert
    suspend fun insert(mahasiswa: DataMahasiswa)

    @Update
    suspend fun update(mahasiswa: DataMahasiswa)

    @Query("SELECT * FROM mahasiswa ORDER BY nomor")
    fun getMahasiswa(): Flow<List<DataMahasiswa>>

    @Query("SELECT * FROM mahasiswa WHERE id = :id")
    suspend fun getMahasiswaById(id: Long): DataMahasiswa?

    @Query("DELETE FROM mahasiswa WHERE id = :id")
    suspend fun deleteById(id: Long)
}