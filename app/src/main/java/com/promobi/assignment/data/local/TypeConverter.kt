package com.promobi.assignment.data.local

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.promobi.assignment.models.Results


/**
 * Created by Vishwajit on 30/07/18.
 */
class TypeConverter {

    var gson = Gson()
    @TypeConverter
    fun stringToList(text: String?): Results? {

        if (text.isNullOrBlank()) {
            return null
        }

        val listType = object : TypeToken<Results>() {}.type
        return gson.fromJson(text, listType)
    }

    @TypeConverter
    fun listToString(lists: Results): String {
        return gson.toJson(lists)
    }
}