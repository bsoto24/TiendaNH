package com.sunafil.mitienda.feature.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sunafil.mitienda.databinding.ActivityDetailBinding
import com.sunafil.mitienda.feature.products.domain.Producto

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val producto: Producto? = intent.getParcelableExtra("producto")
        producto?.let {
            binding.tvNombre.text = it.nombre
            binding.tvPrecio.text = it.precio
            Glide.with(this)
                .load(it.imagen)
                .centerCrop()
                .into(binding.imagen)
        }

    }

}