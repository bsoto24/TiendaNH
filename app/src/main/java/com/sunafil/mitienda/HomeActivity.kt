package com.sunafil.mitienda

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.sunafil.mitienda.databinding.ActivityHomeBinding
import kotlinx.coroutines.MainScope


/**
 * HomeActivity
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 7/12/22
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding // lateinit: inicializar la variable despues

    private val scope = MainScope()

    //cuando se crea la pantalla y nos permite enlazar la vista
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater) //generar la pantalla
        setContentView(binding.root) //enlazar la vista con nuestro activity

        binding.edtBuscador.doOnTextChanged { text, start, before, count ->
            binding.tvNombre.text = "${text} - ${start} - ${before} - ${count}"
        }

        binding.radio.setOnCheckedChangeListener { p0, p1 ->
            binding.tvNombre.text = "${p1}"
        }

        binding.seekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                binding.tvNombre.text = "${p0?.progress}"
            }
        })

        binding.edtBuscador.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus.not()) {
                view.hideKeyboard()
            }
        }

    }

    fun hideKeyboard(view: View) {
        val inputMethodManager: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}

fun View.hideKeyboard() {
    val inputMethodManager: InputMethodManager =
        context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}