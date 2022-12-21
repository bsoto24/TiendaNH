package com.sunafil.mitienda.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sunafil.mitienda.feature.products.domain.Producto


/**
 * ProductoDAO
 *
 * @author Bryam Soto
 * @since 16/12/22
 */
@Dao
interface ProductoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productos: List<Producto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(producto: Producto)

    @Query("DELETE FROM producto")
    suspend fun removeAll()

    @Query("SELECT * FROM producto")
    suspend fun getAll(): List<Producto>

}