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
    private lateinit var spHelper: SPHelper

    // Se ejecuta cuando se crea la pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spHelper = SPHelper(this)

        binding.checkbox.isChecked = spHelper.readBoolean("my_check")
        binding.edtCorreo.setText(spHelper.readString("my_email"))

        binding.btnIngresar.setOnClickListener {
            val check = binding.checkbox.isChecked
            val correo = binding.edtCorreo.text.toString()

            if (check){
                spHelper.saveString("my_email", correo)
            }
            spHelper.saveBoolean("my_check", check)

        }

    }

}