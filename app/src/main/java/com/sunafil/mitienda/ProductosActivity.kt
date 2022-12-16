package com.sunafil.mitienda

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sunafil.mitienda.databinding.ActivityProductosBinding


/**
 * ProductosActivity
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 12/12/22
 */
class ProductosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductosBinding
    private lateinit var spHelper: SPHelper

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spHelper = SPHelper(this)

        counter = spHelper.readInt("contador_1")
        Toast.makeText(this, "${counter}", Toast.LENGTH_SHORT).show()

        val adapter: ProductosAdapter = ProductosAdapter(object : ProductosAdapter.ProductListener {

            override fun onClick(product: Producto) {
                Toast.makeText(
                    this@ProductosActivity,
                    "Producto: ${product.nombre}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onLongClick() {
                Toast.makeText(
                    this@ProductosActivity,
                    "Tienes que darle un click rapido",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
        binding.rvProductos.adapter = adapter //vinculamos el recyclerview con su adapter

        adapter.addItems(ArrayList(obtenerProductos()))

        binding.btnAdd.setOnClickListener {
            counter++
            spHelper.saveInt("contador_1", counter)
            guardarProducto(Producto(counter, "", "Producto $counter", "S/$counter.00"))
            adapter.refreshItems(ArrayList(obtenerProductos()))
        }

    }


    fun obtenerProductos(): List<Producto> {
        return AppDatabase.getDatabase(this).productoDao().getAll()
    }

    fun guardarProducto(producto: Producto) {
        AppDatabase.getDatabase(this).productoDao().insert(producto)
    }


}