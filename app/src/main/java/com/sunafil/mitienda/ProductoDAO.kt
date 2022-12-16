package com.sunafil.mitienda

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * ProductoDAO
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 16/12/22
 */
@Dao
interface ProductoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(productos: List<Producto>)

    @Insert
    fun insert(producto: Producto)

    @Query("DELETE FROM producto")
    fun removeAll()

    @Query("SELECT * FROM producto")
    fun getAll(): List<Producto>

}