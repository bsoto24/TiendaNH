package com.sunafil.mitienda.feature.products.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sunafil.mitienda.databinding.FragmentProductListBinding
import com.sunafil.mitienda.feature.products.domain.Producto
import com.sunafil.mitienda.feature.products.view.ProductosActivity
import com.sunafil.mitienda.feature.products.view.ProductosAdapter
import com.sunafil.mitienda.feature.products.view.ProductosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductosViewModel by viewModels()
    private lateinit var adapter: ProductosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initListeners()
        initObservers()
        viewModel.obtenerProductos()
    }


    private fun initUI() {
        adapter = ProductosAdapter(object : ProductosAdapter.ProductListener {

            override fun onClick(product: Producto) {
                findNavController().navigate(
                    ProductListFragmentDirections.actionProductListFragmentToDetailProductFragment(
                        product
                    )
                )
            }

        })
        binding.rvProductos.adapter = adapter //vinculamos el recyclerview con su adapter
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.guardarProducto()
        }
    }

    private fun initObservers() {
        viewModel.productos.observe(viewLifecycleOwner) {
            adapter.refreshItems(ArrayList(it))
        }

        viewModel.loader.observe(viewLifecycleOwner) {
            (requireActivity() as ProductosActivity).apply {
                if (it) showLoader() else hideLoader()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as ProductosActivity).setTitle("Productos")
    }

}