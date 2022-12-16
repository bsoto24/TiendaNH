package com.sunafil.mitienda

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sunafil.mitienda.databinding.ActivityProductosBinding
import com.sunafil.mitienda.test.SPHelper


/**
 * ProductosActivity
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 12/12/22
 */
class ProductosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductosBinding
    private lateinit var dbHelper: DBHelper
    private lateinit var spHelper: SPHelper

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)
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

        adapter.addItems(obtenerProductos())

        binding.btnAdd.setOnClickListener {
            counter++
            spHelper.saveInt("contador_1", counter)
            guardarProducto(Producto("", "Producto $counter", "S/$counter.00"))
            adapter.refreshItems(obtenerProductos())
        }

    }


    fun obtenerProductos(): ArrayList<Producto> {
        val db = dbHelper.readableDatabase
        val lista = arrayListOf<Producto>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${DBHelper.TABLE_PRODUCTOS}", null)

        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    Producto(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    fun guardarProducto(producto: Producto): Long {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DBHelper.COLUM_PRODUCT_IMAGE, producto.imagen)
            put(DBHelper.COLUM_PRODUCT_NAME, producto.nombre)
            put(DBHelper.COLUM_PRODUCT_PRICE, producto.precio)
        }

        val newRowId = db?.insert(DBHelper.TABLE_PRODUCTOS, null, values)
        return newRowId ?: 0
    }


}