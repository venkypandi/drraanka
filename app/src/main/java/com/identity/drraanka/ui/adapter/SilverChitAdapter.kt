package com.identity.drraanka.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.identity.drraanka.databinding.LayoutSilverChitItemBinding

class SilverChitAdapter(private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<SilverChitAdapter.SilverChitViewHolder>() {

    private val dataList = ArrayList<String>()

    inner class SilverChitViewHolder(private val binding: LayoutSilverChitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(dataText: String, clickListener: (String) -> Unit) {
                binding.tvSchemeName.text = dataText
                binding.tvSchemeDateValue.text = "22-01-2023"
                binding.tvSchemePendingValue.text = "2 Months"
                binding.mcViewSilverChit.setOnClickListener {
                    clickListener(dataText)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SilverChitViewHolder {
        val binding = LayoutSilverChitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SilverChitViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: SilverChitViewHolder, position: Int) {
        holder.bind(dataList[position], clickListener)
    }

    fun setDataList(data: List<String>) {
        dataList.clear()
        dataList.addAll(data)
    }
}