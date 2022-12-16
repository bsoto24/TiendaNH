package com.sunafil.mitienda.test

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import com.sunafil.mitienda.ProductosActivity
import com.sunafil.mitienda.R
import com.sunafil.mitienda.databinding.ActivityTestBinding


/**
 * TestActivity
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 14/12/22
 */
class TestActivity: AppCompatActivity() {
    
    private lateinit var binding: ActivityTestBinding

    //Solo para la pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        initListener()
    }

    fun initUI(){

    }

    fun initListener(){
        binding.seekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.btn.text = "${p1}"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                Snackbar.make(binding.root, "Selecciono: ${p0?.progress}", Snackbar.LENGTH_SHORT).show()
            }

        })

        binding.btn.setOnClickListener {

        }

        binding.edt.doOnTextChanged { text, start, before, count ->
            binding.btn.text = text
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menuprincipal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.option1 -> {
                val intent = Intent(this, ProductosActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.option2 -> {
                Toast.makeText(this, "Opcion 2", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.option3 -> {
                Toast.makeText(this, "Opcion 3", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }


}