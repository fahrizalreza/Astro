package com.astro.test.rezafahrizal.utilities

import androidx.room.TypeConverter
import com.astro.test.rezafahrizal.model.Parent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ParentConverter {
    @TypeConverter
    fun stringToList(data: String?): Parent? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<Parent>() {

        }.type
        return gson.fromJson<Parent>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: Parent): String {
        val gson = Gson()
        return gson.toJson(someObjects)
    }
}