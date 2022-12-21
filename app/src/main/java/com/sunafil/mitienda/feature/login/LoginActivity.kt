package com.sunafil.mitienda.feature.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sunafil.mitienda.databinding.ActivityLoginBinding
import com.sunafil.mitienda.utils.showTimeDialog


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // Se ejecuta cuando se crea la pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
            showTimeDialog(this@LoginActivity)
        }

    }

}