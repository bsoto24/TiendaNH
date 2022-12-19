package com.sunafil.mitienda.feature.products.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.sunafil.mitienda.databinding.ActivityProductosBinding
import com.sunafil.mitienda.feature.detail.DetailActivity
import com.sunafil.mitienda.feature.products.domain.Producto
import dagger.hilt.android.AndroidEntryPoint


/**
 * ProductosActivity
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 12/12/22
 */
@AndroidEntryPoint
class ProductosActivity : AppCompatActivity() {

    private val viewModel: ProductosViewModel by viewModels()
    private lateinit var binding: ActivityProductosBinding
    private lateinit var adapter: ProductosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        initListeners()
        initObservers()
        viewModel.obtenerProductos()
    }

    private fun initUI() {
        adapter = ProductosAdapter(object : ProductosAdapter.ProductListener {

            override fun onClick(product: Producto) {
                val intent = Intent(this@ProductosActivity, DetailActivity::class.java)
                intent.putExtra("producto", product)
                startActivity(intent)
            }

        })
        binding.rvProductos.adapter = adapter //vinculamos el recyclerview con su adapter
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.guardarProducto()
        }
    }

    private fun initObservers() {
        viewModel.productos.observe(this) {
            adapter.refreshItems(ArrayList(it))
        }

        viewModel.loader.observe(this) {
            binding.llLoader.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) {
            Snackbar.make(binding.root, it, Toast.LENGTH_SHORT).show()
        }
    }

}