package com.sunafil.mitienda.feature.products.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sunafil.mitienda.databinding.FragmentDetailProductBinding
import com.sunafil.mitienda.feature.products.view.ProductosActivity

class DetailProductFragment : Fragment() {

    private lateinit var binding: FragmentDetailProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailProductBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initListeners()
    }

    private fun initUI() {
        val producto = DetailProductFragmentArgs.fromBundle(
            requireArguments()
        ).product
        binding.tvNombre.text = producto.nombre
        binding.tvPrecio.text = producto.precio
        Glide.with(this)
            .load(producto.imagen)
            .centerCrop()
            .into(binding.imagen)
    }

    private fun initListeners() {
        binding.imagen.setOnClickListener {
            findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToInformationDialog())
        }
        binding.fabCart.setOnClickListener {
            findNavController().navigate(DetailProductFragmentDirections.actionDetailProductFragmentToAddCartBottomSheet())
        }
        setFragmentResultListener("contador_bs"){ _, bundle ->
            val contador = bundle.getInt("contador", 0)
            if (contador >= 0) {
                Snackbar.make(binding.root, "Se agregaron $contador productos a tu carrito", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as ProductosActivity).setTitle("Detalle")
    }

}