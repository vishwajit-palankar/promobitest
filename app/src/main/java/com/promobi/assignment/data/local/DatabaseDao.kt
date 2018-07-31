package com.promobi.assignment.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.promobi.assignment.models.NewsResponseSchema
import io.reactivex.Maybe


/**
 * Created by Vishwajit on 31/07/18.
 */

@Dao
interface DatabaseDao {


    @Insert(onConflict = REPLACE)
    fun saveResults(states: NewsResponseSchema)

    @Query("SELECT * FROM NewsResponseSchema")
    fun getNewsResponseSchema(): Maybe<NewsResponseSchema>

    @Query("DELETE FROM NewsResponseSchema")
    fun deleteAll()
}