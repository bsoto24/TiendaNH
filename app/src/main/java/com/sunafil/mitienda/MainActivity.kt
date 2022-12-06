package com.sunafil.mitienda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sunafil.mitienda.databinding.ActivityMainBinding

const val CORREO_EXITO = "bsoto.dev@gmail.com"
const val PASSWORD_EXITO = "1234"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Se ejecuta cuando se crea la pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
            binding.btnIngresar.text = "Otro nombre"

            if (binding.edtCorreo.text.toString() == CORREO_EXITO && binding.edtPassword.text.toString() == PASSWORD_EXITO) {
                Toast.makeText(this, "Login satisfactorio", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

    }

}