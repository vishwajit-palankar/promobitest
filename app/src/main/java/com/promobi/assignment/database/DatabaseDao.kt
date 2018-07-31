package com.promobi.assignment.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.TypeConverters
import com.promobi.assignment.models.Results


/**
 * Created by Vishwajit on 31/07/18.
 */

@Dao
interface DatabaseDao {


    @Insert(onConflict = REPLACE)
    fun saveResults(states: Results)

    @Query("SELECT * FROM Results")
    fun getResults(): LiveData<List<Results>>

    @Query("DELETE FROM Results")
    fun deleteAll()
}