package com.sunafil.mitienda.data.di

import android.content.Context
import androidx.room.Room
import com.sunafil.mitienda.data.local.database.AppDatabase
import com.sunafil.mitienda.data.local.database.AppDatabase.Companion.MY_DB
import com.sunafil.mitienda.data.local.database.ProductoDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * DatabaseModule
 *
 * @author Bryam Soto
 * @since 19/12/22
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideProductoDao(appDatabase: AppDatabase): ProductoDAO {
        return appDatabase.productoDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            MY_DB,
        ).build()
    }

}