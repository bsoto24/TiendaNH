package com.sunafil.mitienda

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sunafil.mitienda.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Se ejecuta cuando se crea la pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MainActivity", "onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
//            Log.e("MainActivity", "Le dio click al boton")
//
//
//            binding.btnIngresar.text = "Otro nombre"
//
//            if (binding.edtCorreo.text.toString() == CORREO_EXITO && binding.edtPassword.text.toString() == PASSWORD_EXITO) {
//                val intent: Intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//            } else {
//                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show() //duracion corta
//            }

            val intent: Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onStart() {
        super.onStart()
        Log.e("MainActivity", "onStart")
    }

    // cuando la pantalla es visible y es interactuable
    override fun onResume() {
        super.onResume()
        Log.e("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("MainActivity", "onPause")
    }

    // para guardar datos
    override fun onStop() {
        super.onStop()
        Log.e("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("MainActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("MainActivity", "onRestart")
    }

}