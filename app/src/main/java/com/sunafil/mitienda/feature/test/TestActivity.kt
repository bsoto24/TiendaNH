package com.sunafil.mitienda.feature.test

import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.snackbar.Snackbar
import com.sunafil.mitienda.databinding.ActivityTestBinding


/**
 * TestActivity
 *
 * @author Bryam Soto
 * @since 14/12/22
 */
class TestActivity: AppCompatActivity() {
    
    private lateinit var binding: ActivityTestBinding

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

}