package com.fadhlifirdausi607062300117.projectapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fadhlifirdausi607062300117.projectapp.model.DataMahasiswa

@Database(entities = [DataMahasiswa::class], version = 1, exportSchema = false)
abstract class MahasiswaDb: RoomDatabase() {

    abstract val dao: DataMahasiswaDao

    companion object {

        @Volatile
        private var INSTANCE: MahasiswaDb? = null

        fun getInstance(context: Context): MahasiswaDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MahasiswaDb::class.java,
                        "mahasiswa.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}