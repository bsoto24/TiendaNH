package com.sunafil.mitienda.feature.products.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sunafil.mitienda.databinding.FragmentInformationDialogBinding

class InformationDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentInformationDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationDialogBinding.inflate(inflater)
        isCancelable = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEntendido.setOnClickListener {
            dismissAllowingStateLoss()
        }
        arguments?.let {
            binding.tvTitulo.text = it.getString("title", "")
            binding.tvDescripcion.text = "Las coordenadas son:\n${it.getDouble("latitude", 0.0)}, ${it.getDouble("longitude", 0.0)}"
        }
    }

}