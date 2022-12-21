package com.sunafil.mitienda.feature.products.view.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sunafil.mitienda.databinding.BottomSheetAddCartBinding

class AddCartBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetAddCartBinding

    var contador = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetAddCartBinding.inflate(inflater)
        isCancelable = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEntendido.setOnClickListener {
            setFragmentResult("contador_bs", Bundle().apply {
                putInt("contador", contador)
            })
            dismissAllowingStateLoss()
        }
        binding.btnMinus.setOnClickListener {
            contador--
            binding.tvContador.text = "$contador"
        }
        binding.btnPlus.setOnClickListener {
            contador++
            binding.tvContador.text = "$contador"
        }
    }

}