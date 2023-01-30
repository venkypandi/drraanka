package com.identity.drraanka.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.identity.drraanka.databinding.LayoutCategoriesItemBinding

class CategoriesAdapter(private val context: Context, private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val dataList = ArrayList<String>()

    inner class CategoriesViewHolder(private val binding: LayoutCategoriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String, clickListener: (String) -> Unit, context: Context) {
            binding.tvCategory.text = data
            binding.mcViewCategories.setOnClickListener {
                clickListener(data)
            }
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

    fun setDataList(data: List<String>) {
        dataList.clear()
        dataList.addAll(data)
    }
}