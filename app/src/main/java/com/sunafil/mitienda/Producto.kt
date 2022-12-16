package com.sunafil.mitienda

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Producto
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 12/12/22
 */
@Entity
data class Producto(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "imagen") val imagen: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "precio") val precio: String
)
