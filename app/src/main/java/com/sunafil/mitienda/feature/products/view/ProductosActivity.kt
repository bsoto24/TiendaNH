package com.sunafil.mitienda.feature.products.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sunafil.mitienda.databinding.ActivityProductosBinding
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

    private lateinit var binding: ActivityProductosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun showLoader() {
        binding.llLoader.visibility = View.VISIBLE
    }

    fun hideLoader() {
        binding.llLoader.visibility = View.GONE
    }

    fun setTitle(title: String) {
        binding.tvTitulo.text = title
    }

}