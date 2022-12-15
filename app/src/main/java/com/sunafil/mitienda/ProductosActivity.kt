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
class ProductosActivity: AppCompatActivity() {

    private lateinit var binding: ActivityProductosBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter: ProductosAdapter = ProductosAdapter(object : ProductosAdapter.ProductListener {

            override fun onClick(product: Producto) {
                Toast.makeText(this@ProductosActivity, "Producto: ${product.nombre}", Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick() {
                Toast.makeText(this@ProductosActivity, "Tienes que darle un click rapido", Toast.LENGTH_SHORT).show()
            }

        })
        binding.rvProductos.adapter = adapter //vinculamos el recyclerview con su adapter

        adapter.addItems(obtenerProductos())
        adapter.deleteLastItem()
        adapter.addItem(Producto("Imagen 10", "Producto 10", "S/ 111.11"))

    }


    //generar la lista de productos
    fun obtenerProductos():ArrayList<Producto>{
        val lista = arrayListOf<Producto>(
            Producto("Imagen 1", "Producto 1", "S/ 100.00"),
            Producto("Imagen 2", "Producto 2", "S/ 10.00"),
            Producto("Imagen 3", "Producto 3", "S/ 160.00"),
            Producto("Imagen 4", "Producto 4", "S/ 90.00"),
            Producto("Imagen 5", "Producto 5", "S/ 70.00"),
            Producto("Imagen 6", "Producto 6", "S/ 80.00"),
            Producto("Imagen 7", "Producto 7", "S/ 20.00"),
            Producto("Imagen 8", "Producto 8", "S/ 1.00"),
            Producto("Imagen 9", "Producto 9", "S/ 3.00")
        )
        return lista
    }



}