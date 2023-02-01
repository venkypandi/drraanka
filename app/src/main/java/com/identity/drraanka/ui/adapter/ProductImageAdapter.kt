package com.identity.drraanka.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.identity.drraanka.R
import com.identity.drraanka.databinding.LayoutProductItemBinding


class ProductImageAdapter(
    private val context: Context,
    private val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<ProductImageAdapter.ProductImageViewHolder>() {

    private val dataList = ArrayList<Int>()

    inner class ProductImageViewHolder(private val binding: LayoutProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Int, clickListener: (Int) -> Unit, context: Context) {
            if (position % 2 == 0) {
                binding.ivProduct.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.item1))
                binding.mcViewProduct.setOnClickListener {
                    clickListener(R.drawable.item1)
                }
            } else {
                binding.ivProduct.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.item2))
                binding.mcViewProduct.setOnClickListener {
                    clickListener(R.drawable.item2)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductImageViewHolder {
        val binding = LayoutProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductImageViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ProductImageViewHolder, position: Int)  {
        holder.bind(dataList[position], clickListener, context)
    }

    fun setDataList(data: List<Int>) {
        dataList.clear()
        dataList.addAll(data)
    }
}