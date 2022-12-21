package com.sunafil.mitienda.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sunafil.mitienda.feature.products.domain.Producto


/**
 * AppDatabase
 *
 * @author Bryam Soto
 * @since 16/12/22
 */
@Database(entities = [Producto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productoDao(): ProductoDAO

    companion object {

        const val MY_DB = "my_db"

    }

}