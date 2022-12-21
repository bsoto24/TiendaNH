package com.sunafil.mitienda.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sunafil.mitienda.databinding.ActivityHomeBinding


/**
 * HomeActivity
 *
 * @author Bryam Soto
 * @since 7/12/22
 */
class HomeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding // lateinit: inicializar la variable despues

    //cuando se crea la pantalla y nos permite enlazar la vista
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater) //generar la pantalla
        setContentView(binding.root) //enlazar la vista con nuestro activity


    }


}