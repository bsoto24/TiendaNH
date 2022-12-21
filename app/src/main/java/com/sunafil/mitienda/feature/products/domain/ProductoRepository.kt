package com.sunafil.mitienda.feature.products.domain

import com.sunafil.mitienda.feature.products.domain.Producto


/**
 * ProductoRepository
 *
 * @author Bryam Soto
 * @since 19/12/22
 */
interface ProductoRepository {

    suspend fun obtenerProductos(): Result<List<Producto>>

    suspend fun guardarProducto()

}