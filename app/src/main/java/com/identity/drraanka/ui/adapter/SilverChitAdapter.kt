package com.identity.drraanka.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.identity.drraanka.data.remote.model.CustomerChit
import com.identity.drraanka.databinding.LayoutSilverChitItemBinding

class SilverChitAdapter(private val clickListener: (CustomerChit?) -> Unit) :
    RecyclerView.Adapter<SilverChitAdapter.SilverChitViewHolder>() {

    private val dataList = ArrayList<CustomerChit?>()

    inner class SilverChitViewHolder(private val binding: LayoutSilverChitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(dataText: CustomerChit?, clickListener: (CustomerChit?) -> Unit) {
                binding.tvSchemeName.text = dataText?.chitName
                binding.tvSchemeDateValue.text = dataText?.chitStartDate
                binding.tvSchemePendingValue.text = dataText?.pending
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

    fun setDataList(data: List<CustomerChit?>) {
        dataList.clear()
        dataList.addAll(data)
    }
}