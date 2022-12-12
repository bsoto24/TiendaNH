package com.sunafil.mitienda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunafil.mitienda.databinding.ActivityTempBinding


/**
 * ShoppingCartActivity
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 11/12/22
 */
class ShoppingCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTempBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTempBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ShoppingCartAdapter()
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = adapter

        adapter.addProducts(
            arrayListOf(
                ProductCart("Producto 1", "Precio 1", "Descuento 1"),
                ProductCart("Producto 2", "Precio 2", "Descuento 2"),
                ProductCart("Producto 3", "Precio 3", "Descuento 3"),
                ProductCart("Producto 4", "Precio 4", "Descuento 4"),
                ProductCart("Producto 1", "Precio 1", "Descuento 1"),
                ProductCart("Producto 2", "Precio 2", "Descuento 2"),
                ProductCart("Producto 3", "Precio 3", "Descuento 3"),
                ProductCart("Producto 4", "Precio 4", "Descuento 4")
            )
        )

    }

}