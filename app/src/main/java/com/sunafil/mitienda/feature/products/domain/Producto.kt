package com.sunafil.mitienda.feature.products.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


/**
 * Producto
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 12/12/22
 */
@Entity
@Parcelize
data class Producto(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "imagen") var imagen: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "precio") val precio: String
) : Parcelable
