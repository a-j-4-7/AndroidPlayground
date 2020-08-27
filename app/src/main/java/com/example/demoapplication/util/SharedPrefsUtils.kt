package com.example.demoapplication.util
import android.content.SharedPreferences

class SharedPrefsUtils constructor(
    val sharedPrefs: SharedPreferences,
    val editor: SharedPreferences.Editor
) {

    companion object{
        const val KEY_LOCALE_LANGUAGE = "locale_language"
    }

    fun saveToSharedPrefs(data: String) {
        editor.putString("KEY", data).commit()
    }

    fun getString(keyName: String) = sharedPrefs.getString(keyName, "NULL")

}