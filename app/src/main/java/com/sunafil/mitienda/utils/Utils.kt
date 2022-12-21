package com.sunafil.mitienda.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*


/**
 * Utils
 *
 * @author Bryam Soto
 * @since 14/12/22
 */

fun showTimeDialog(context: Context) {
    //Mostra un dialogo de seleccion de hora reloj

    val calendar = Calendar.getInstance() //obtener la fecha actual
    val hour = calendar[Calendar.HOUR_OF_DAY] //obtengo el año
    val minute = calendar[Calendar.MINUTE]

    val dialog = TimePickerDialog(context, { view, hour, minute ->
        Toast.makeText(context, "Hora: ${hour}:${minute}", Toast.LENGTH_LONG).show()
    }, hour, minute, false)

    dialog.show()
}


fun showSimpleDialog(context: Context) {
    //Mostra un dialogo simple

    val builder = AlertDialog.Builder(context)
    builder.setTitle("Dialogo")
        .setMessage("Este es un dialogo simple")
        .setPositiveButton("Aceptar", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                Toast.makeText(context, "Le dio en aceptar", Toast.LENGTH_SHORT)
                    .show()
            }
        }).setNegativeButton("Cancelar", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                Toast.makeText(context, "Le dio en cancelar", Toast.LENGTH_SHORT)
                    .show()
            }
        }).setNeutralButton("Omitir", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                Toast.makeText(context, "Le dio en Omitir", Toast.LENGTH_SHORT).show()
            }
        })
        .show()

}


fun showDateDialog(context: Context) {
    //Mostra un dialogo de seleccion de fecha calendario

    val calendar = Calendar.getInstance() //obtener la fecha actual
    val year = calendar[Calendar.YEAR] //obtengo el año
    val month = calendar[Calendar.MONTH]
    val day = calendar[Calendar.DAY_OF_MONTH]

    val dialog = DatePickerDialog(context, { view, year, month, day ->
        Toast.makeText(context, "Fecha: ${year}-${month+1}-${day}", Toast.LENGTH_LONG).show()
    }, year, month, day)

    dialog.show()

}
