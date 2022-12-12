package com.sunafil.mitienda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.sunafil.mitienda.databinding.ItemProductBinding


/**
 * ShoppingCartAdapter
 *
 * @author Bryam Soto - Interbank
 * @email bsoto@intercorp.com.pe
 * @since 11/12/22
 */
class ShoppingCartAdapter : RecyclerView.Adapter<ShoppingCartAdapter.MyVH>() {

    val items: ArrayList<ProductCart> = arrayListOf()

    fun addProducts(items: ArrayList<ProductCart>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addProduct(item: ProductCart) {
        this.items.add(item)
        notifyDataSetChanged()
    }


    class MyVH(val binding: ItemProductBinding) : ViewHolder(binding.root) {

        fun bind(item: ProductCart) {
            binding.tvName.text = item.name
            binding.tvPrice.text = item.price
            binding.tvDiscount.text = item.discount
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        return MyVH(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}