package com.identity.drraanka.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.identity.drraanka.R
import com.identity.drraanka.databinding.LayoutFilterListItemBinding

class FilterAdapter(private val context: Context, private val clickListener: (String, Int) -> Unit) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    private val dataList = mutableMapOf<String,Boolean>()

    inner class FilterViewHolder(private val binding: LayoutFilterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: MutableMap.MutableEntry<String, Boolean>,
            position: Int,
            clickListener: (String, Int) -> Unit,
            context: Context
        ) {
            binding.tvFilterItem.text = data.key
            if (data.value) {
                binding.mcViewFilter.setCardBackgroundColor(ContextCompat.getColor(context, R.color.text_blue))
                binding.tvFilterItem.setTextColor(ContextCompat.getColor(context, R.color.white))
            } else {
                binding.mcViewFilter.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                binding.tvFilterItem.setTextColor(ContextCompat.getColor(context, R.color.black))
            }
            binding.mcViewFilter.setOnClickListener {
                clickListener(data.key, position)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val binding = LayoutFilterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(dataList.entries.elementAt(position), position, clickListener, context)
    }

    fun setDataList(data: Map<String, Boolean>) {
        dataList.clear()
        data.forEach {
            dataList[it.key] = it.value
        }
    }
}