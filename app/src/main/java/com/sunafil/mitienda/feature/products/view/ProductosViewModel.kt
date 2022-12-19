package com.sunafil.mitienda.feature.products.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunafil.mitienda.data.local.preferences.SPHelper
import com.sunafil.mitienda.data.local.preferences.SPHelper.Companion.PREF_CONTADOR
import com.sunafil.mitienda.feature.products.domain.ProductoRepository
import com.sunafil.mitienda.feature.products.domain.Producto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * ActivityViewModel
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 19/12/22
 */
@HiltViewModel
class ProductosViewModel @Inject constructor(
    val repository: ProductoRepository,
    val spHelper: SPHelper
) : ViewModel() {

    private val _productos: MutableLiveData<List<Producto>> = MutableLiveData()
    val productos: LiveData<List<Producto>> = _productos

    private val _loader: MutableLiveData<Boolean> = MutableLiveData()
    val loader: LiveData<Boolean> = _loader

    fun obtenerProductos() {
        viewModelScope.launch {
            _loader.value = true
            delay(2000)
            _productos.value = repository.obtenerProductos()
            _loader.value = false
        }
    }

    fun guardarProducto() {
        viewModelScope.launch {
            _loader.value = true
            val counter = spHelper.readInt(PREF_CONTADOR) + 1
            val producto =
                Producto(counter, "Imagen $counter", "Producto $counter", "S/$counter.00")
            repository.guardarProducto(producto)
            spHelper.saveInt(PREF_CONTADOR, counter)
            delay(2000)
            _productos.value = repository.obtenerProductos()
            _loader.value = false
        }
    }

    fun obtenerImagenes() {
        viewModelScope.launch {
            val imagenes = repository.obtenerImagenes()
            val productosUpdate = productos.value ?: listOf()
            productosUpdate.forEachIndexed { index, producto ->
                if (imagenes.size >= productosUpdate.size) {
                    producto.imagen = imagenes[index]
                } else if (imagenes.isNotEmpty()) {
                    producto.imagen = imagenes[0]
                }
            }
            _productos.value = productosUpdate
        }
    }

}