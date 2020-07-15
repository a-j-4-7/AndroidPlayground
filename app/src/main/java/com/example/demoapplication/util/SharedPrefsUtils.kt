package com.example.demoapplication.util
import android.content.SharedPreferences

class SharedPrefsUtils constructor(
    val sharedPrefs: SharedPreferences,
    val editor: SharedPreferences.Editor
) {

    fun saveToSharedPrefs(data: String) {
        editor.putString("KEY", data).commit()
    }

    fun getString(keyName: String) = sharedPrefs.getString(keyName, "NULL")

}