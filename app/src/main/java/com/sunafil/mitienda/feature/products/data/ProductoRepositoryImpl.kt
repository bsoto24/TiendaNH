package com.sunafil.mitienda.feature.products.data

import com.sunafil.mitienda.data.local.database.ProductoDAO
import com.sunafil.mitienda.feature.products.domain.ProductoRepository
import com.sunafil.mitienda.feature.products.domain.Producto
import javax.inject.Inject


/**
 * ProductoRepository
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 19/12/22
 */
class ProductoRepositoryImpl @Inject constructor(
    private val productoDAO: ProductoDAO
) : ProductoRepository {

    override suspend fun obtenerProductos(): List<Producto> {
        return productoDAO.getAll()
    }

    override suspend fun guardarProducto(producto: Producto) {
        productoDAO.insert(producto)
    }

}