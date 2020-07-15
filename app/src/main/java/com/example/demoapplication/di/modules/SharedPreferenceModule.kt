package com.example.demoapplication.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.demoapplication.util.SharedPrefsUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object SharedPreferenceModule {

    @Singleton
    @Provides
    fun providesSharedPreference(@ApplicationContext context: Context) =
        context.getSharedPreferences("my_shared_prefs",Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesSharedPreferenceEditor(sharedPreferences: SharedPreferences) = sharedPreferences.edit()

    @Singleton
    @Provides
    fun providesSharedPrefsUtils(sharedPrefs: SharedPreferences,
                                 editor: SharedPreferences.Editor) =
        SharedPrefsUtils(sharedPrefs,editor)
}