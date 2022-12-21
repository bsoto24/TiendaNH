package com.sunafil.mitienda.data.di

import android.content.Context
import com.sunafil.mitienda.data.local.preferences.SPHelper
import com.sunafil.mitienda.data.local.preferences.SPHelper.Companion.MY_PREF
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * SharedPreferenceModule
 *
 * @author Bryam Soto
 * @since 19/12/22
 */
@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SPHelper {
        return SPHelper(context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE))
    }

}