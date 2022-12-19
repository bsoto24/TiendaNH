package com.sunafil.mitienda.feature.products.data

import com.sunafil.mitienda.data.local.database.ProductoDAO
import com.sunafil.mitienda.data.local.preferences.SPHelper
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
    private val apiService: ApiService,
    private val spHelper: SPHelper
) : ProductoRepository {

    override suspend fun obtenerProductos(): Result<List<Producto>> {

        val productos = productoDAO.getAll()

        val call = apiService.getDogsByBread()
        val imagenes: List<String> = if (call.isSuccessful) {
            call.body()?.images ?: listOf()
        } else {
            return Result.failure(Throwable("Algo salió mal"))
        }

        if (imagenes.isNotEmpty()) {
            productos.forEachIndexed { index, producto ->
                if (imagenes.size >= productos.size) {
                    producto.imagen = imagenes[index]
                } else {
                    producto.imagen = imagenes[0]
                }
            }
        } else {
            return Result.failure(Throwable("Algo salió mal"))
        }

        return Result.success(productos)
    }

    override suspend fun guardarProducto() {
        val counter = spHelper.readInt(SPHelper.PREF_CONTADOR) + 1
        val producto =
            Producto(counter, "Imagen $counter", "Producto $counter", "S/$counter.00")
        productoDAO.insert(producto)
        spHelper.saveInt(SPHelper.PREF_CONTADOR, counter)
    }

}