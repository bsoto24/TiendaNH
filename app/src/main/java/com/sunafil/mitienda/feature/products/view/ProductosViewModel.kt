package com.sunafil.mitienda.feature.products.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunafil.mitienda.feature.products.domain.Producto
import com.sunafil.mitienda.feature.products.domain.ProductoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val repository: ProductoRepository
) : ViewModel() {

    private val _productos: MutableLiveData<List<Producto>> = MutableLiveData()
    val productos: LiveData<List<Producto>> = _productos

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    private val _loader: MutableLiveData<Boolean> = MutableLiveData()
    val loader: LiveData<Boolean> = _loader

    fun obtenerProductos() {
        viewModelScope.launch {
            _loader.value = true
            repository.obtenerProductos()
                .onSuccess {
                    _productos.value = it
                }.onFailure {
                    _error.value = it.message
                }
            _loader.value = false
        }
    }

    fun guardarProducto() {
        viewModelScope.launch {
            _loader.value = true
            repository.guardarProducto()
            obtenerProductos()
            _loader.value = false
        }
    }

}