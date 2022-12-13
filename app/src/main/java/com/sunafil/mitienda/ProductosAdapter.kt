package com.sunafil.mitienda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunafil.mitienda.databinding.ItemProductoBinding


/**
 * ProductosAdapter
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 12/12/22
 */
class ProductosAdapter: RecyclerView.Adapter<ProductosAdapter.ProductoVH>() {

    val items: ArrayList<Producto> = arrayListOf()

    fun addItems(productos: ArrayList<Producto>){
        items.addAll(productos)
        notifyDataSetChanged() //vuelve a repintar la lista de items
    }

    fun addItem(producto: Producto){
        items.add(producto)
        notifyDataSetChanged()
    }

    fun deleteLastItem(){
        items.removeLast()
        notifyDataSetChanged()
    }

    //Controlar los elementos visuales del item
    class ProductoVH(val binding: ItemProductoBinding): RecyclerView.ViewHolder(binding.root) {

        //inyectar información
        fun bind(item: Producto){
            binding.tvNombre.text = item.nombre
            binding.tvPrecio.text = item.precio
        }

    }

    //Crear y renderizar la vista del item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoVH {
        return ProductoVH(ItemProductoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    //Cambiar la informacion del item
    override fun onBindViewHolder(holder: ProductoVH, position: Int) {
        holder.bind(items[position])
    }

    //Asignar el tamaño de tu lista de items
    override fun getItemCount(): Int {
        return items.size
    }

}