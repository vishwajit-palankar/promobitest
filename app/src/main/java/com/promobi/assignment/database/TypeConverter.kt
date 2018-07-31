package com.promobi.assignment.database

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.promobi.assignment.models.Lists
import java.util.*


/**
 * Created by Vishwajit on 30/07/18.
 */
class TypeConverter {

    var gson = Gson()
    @TypeConverter
    fun stringToList(text: String?): List<Lists> {

        if (text.isNullOrBlank()) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Lists>>() {}.type
        return gson.fromJson(text, listType)
    }

    @TypeConverter
    fun listToString(lists: List<Lists>): String {
        return gson.toJson(lists)
    }
}