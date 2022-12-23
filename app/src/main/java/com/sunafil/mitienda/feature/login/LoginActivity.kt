package com.sunafil.mitienda.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sunafil.mitienda.databinding.ActivityLoginBinding
import com.sunafil.mitienda.feature.maps.view.MapsActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // Se ejecuta cuando se crea la pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
            Firebase.auth.signInWithEmailAndPassword( // Login usando firebase
                binding.edtCorreo.text.toString(),
                binding.edtPassword.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) { // login exitoso
                        val intent = Intent(this, MapsActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else { // credenciales incorrectas
                        Snackbar.make(
                            binding.root, "Credenciales incorrectas",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
        }

    }

}