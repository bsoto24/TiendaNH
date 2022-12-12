package com.sunafil.mitienda

import android.app.*
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.android.material.snackbar.Snackbar
import com.sunafil.mitienda.databinding.ActivityMainBinding
import java.util.*


const val CORREO_EXITO = "bsoto.dev@gmail.com"
const val PASSWORD_EXITO = "1234"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
//            if (binding.edtCorreo.text.toString() == CORREO_EXITO && binding.edtPassword.text.toString() == PASSWORD_EXITO) {
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//            } else {
//                Snackbar.make(binding.root, "Credenciales incorrectas", Snackbar.LENGTH_SHORT)
//                    .show()
//            }
            showNotification()
        }

    }

    fun showSimpleDialog() {
        //Mostrar
        val builder = AlertDialog.Builder(this)

        //Configurar dialog en pantalla
        builder.setMessage("Este es un dialogo simple")
            .setPositiveButton("Aceptar") { dialog, id ->
                Toast.makeText(this, "Aceptar", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar") { dialog, id ->
                Toast.makeText(this, "Cancelar", Toast.LENGTH_SHORT).show()
            }

        //Mostrar dialog en pantalla
        builder.show()
    }

    fun showDateDialog() {
        //Obtener fecha actual
        val c = Calendar.getInstance()
        val mHour = c.get(Calendar.HOUR_OF_DAY)
        val mMinute = c.get(Calendar.MINUTE)

        //Configurar dialog con la hora actual
        val timePickerDialog = TimePickerDialog(
            this,
            { view, hourOfDay, minute ->
                Toast.makeText(this, "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
            },
            mHour,
            mMinute,
            false
        )

        //Mostrar dialog en pantalla
        timePickerDialog.show()
    }

    fun showTimeDialog() {
        //Obtener fecha actual
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDay = c[Calendar.DAY_OF_MONTH]

        //Configurar dialog con la fecha actual seleccionada
        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                Toast.makeText(
                    this,
                    dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year,
                    Toast.LENGTH_SHORT
                ).show()
            },
            mYear,
            mMonth,
            mDay
        )

        //Mostrar dialog en pantalla
        datePickerDialog.show()
    }

    fun showNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val mNotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val mChannel = NotificationChannel(
                "my_channel_01",
                "my_channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            mChannel.description = "My channel 01"
            mChannel.enableLights(true)
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            mNotificationManager.createNotificationChannel(mChannel)

            val mBuilder: NotificationCompat.Builder =
                NotificationCompat.Builder(applicationContext, "my_channel_01")
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setTicker("Hearty365")
                    .setPriority(Notification.PRIORITY_MAX) // this is deprecated in API 26 but you can still use for below 26. check below update for 26 API
                    .setContentTitle("Default notification")
                    .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                    .setContentInfo("Info")
            mNotificationManager.notify(1, mBuilder.build())

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.opcion1 -> {
                Snackbar.make(binding.root, "Opcion 1", Snackbar.LENGTH_SHORT)
                    .show()
                true
            }
            R.id.opcion2 -> {
                Snackbar.make(binding.root, "Opcion 2", Snackbar.LENGTH_SHORT)
                    .show()
                true
            }
            R.id.opcion3 -> {
                Snackbar.make(binding.root, "Opcion 3", Snackbar.LENGTH_SHORT)
                    .show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}