package com.sunafil.mitienda.feature.products.domain

import com.sunafil.mitienda.feature.products.domain.Producto


/**
 * ProductoRepository
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 19/12/22
 */
interface ProductoRepository {

    suspend fun obtenerProductos():List<Producto>

    suspend fun guardarProducto(producto: Producto)

    suspend fun obtenerImagenes(): List<String>

}