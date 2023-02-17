package com.identity.drraanka.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.identity.drraanka.R
import com.identity.drraanka.data.remote.model.Product
import com.identity.drraanka.data.remote.model.ProductCategory
import com.identity.drraanka.databinding.LayoutCategoriesItemBinding

class CategoriesAdapter(private val context: Context, private val clickListener: (Product) -> Unit) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val dataList = ArrayList<Product>()

    inner class CategoriesViewHolder(private val binding: LayoutCategoriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Product, clickListener: (Product) -> Unit, context: Context) {
            binding.tvCategory.text = data.productName
            binding.mcViewCategories.setOnClickListener {
                clickListener(data)
            }

            Glide.with(context)
                .load(data.productImages?.attachmentList?.get(0)?.image)
                .error(R.drawable.alert)
                .into(binding.ivCategory)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = LayoutCategoriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(dataList[position], clickListener, context)
    }

    fun setDataList(data: List<Product>) {
        dataList.clear()
        dataList.addAll(data)
    }
}