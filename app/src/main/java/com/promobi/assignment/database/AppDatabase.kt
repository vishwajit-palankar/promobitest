package com.promobi.assignment.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.promobi.assignment.models.Results


/**
 * Created by Vishwajit on 31/07/18.
 */
@Database(entities = [(Results::class)], version = 1)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "result_db")
                        .build()
            }
            return instance!!
        }
    }


    abstract fun databaseDao(): DatabaseDao
}