package com.sunafil.mitienda

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sunafil.mitienda.databinding.ActivityMainBinding
import java.util.Calendar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Se ejecuta cuando se crea la pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
            showTimeDialog()
        }

    }

    fun showSimpleDialog() {
        //Mostra un dialogo simple

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Dialogo")
            .setMessage("Este es un dialogo simple")
            .setPositiveButton("Aceptar", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(this@MainActivity, "Le dio en aceptar", Toast.LENGTH_SHORT)
                        .show()
                }
            }).setNegativeButton("Cancelar", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(this@MainActivity, "Le dio en cancelar", Toast.LENGTH_SHORT)
                        .show()
                }
            }).setNeutralButton("Omitir", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(this@MainActivity, "Le dio en Omitir", Toast.LENGTH_SHORT).show()
                }
            })
            .show()

    }


    fun showDateDialog() {
        //Mostra un dialogo de seleccion de fecha calendario

        val calendar = Calendar.getInstance() //obtener la fecha actual
        val year = calendar[Calendar.YEAR] //obtengo el año
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val dialog = DatePickerDialog(this, { view, year, month, day ->
            Toast.makeText(this, "Fecha: ${year}-${month+1}-${day}", Toast.LENGTH_LONG).show()
        }, year, month, day)

        dialog.show()

    }


    fun showTimeDialog() {
        //Mostra un dialogo de seleccion de hora reloj

        val calendar = Calendar.getInstance() //obtener la fecha actual
        val hour = calendar[Calendar.HOUR_OF_DAY] //obtengo el año
        val minute = calendar[Calendar.MINUTE]

        val dialog = TimePickerDialog(this, { view, hour, minute ->
            Toast.makeText(this, "Hora: ${hour}:${minute}", Toast.LENGTH_LONG).show()
        }, hour, minute, false)

        dialog.show()

    }

}