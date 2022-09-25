package com.astro.test.rezafahrizal.utilities

import android.content.Context
import android.content.SharedPreferences

object Utilities {

    // save value into shared preference
    fun saveDataPreference(
        context: Context,
        preferenceName: String,
        keyContent: String,
        value: String
    ) {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit().apply {
            putString(keyContent, value)
        }
        editor.apply()
    }

    // retrieve data from shared preference
    fun getDataPref(context: Context, KEY_NAME: String?): String? {
        val sharedPref: SharedPreferences =
            context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        return sharedPref.getString(KEY_NAME, "")
    }

}