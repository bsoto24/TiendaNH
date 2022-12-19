package com.sunafil.mitienda.feature.products.data

import com.sunafil.mitienda.data.local.database.ProductoDAO
import com.sunafil.mitienda.data.remote.ApiService
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
    private val productoDAO: ProductoDAO,
    private val apiService: ApiService
) : ProductoRepository {

    override suspend fun obtenerProductos(): List<Producto> {
        return productoDAO.getAll()
    }

    override suspend fun guardarProducto(producto: Producto) {
        productoDAO.insert(producto)
    }

    override suspend fun obtenerImagenes(): List<String> {
        val call = apiService.getDogsByBread()
        if (call.isSuccessful) {
            return call.body()?.images ?: listOf()
        }
        return listOf()
    }

}