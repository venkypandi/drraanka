package com.identity.drraanka.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.identity.drraanka.databinding.LayoutSchemeDetailsItemBinding

class SchemeDetailsAdapter(private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<SchemeDetailsAdapter.SchemeDetailsViewHolder>() {

    private val dataList = ArrayList<String>()

    inner class SchemeDetailsViewHolder(private val binding: LayoutSchemeDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String, clickListener: (String) -> Unit, position: Int) {
            binding.tvEmi.text = data
            binding.tvEmiDate.text = data
            binding.tvEmiNumber.text = data
            if (position%2 ==0) {
                binding.ivPaymentDone.visibility = View.VISIBLE
                binding.ivPaymentPending.visibility = View.GONE
                binding.ivPayment.visibility = View.GONE
            } else {
                binding.ivPaymentDone.visibility = View.GONE
                binding.ivPaymentPending.visibility = View.VISIBLE
                binding.ivPayment.visibility = View.VISIBLE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchemeDetailsViewHolder {
        val binding = LayoutSchemeDetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchemeDetailsViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: SchemeDetailsViewHolder, position: Int) {
        holder.bind(dataList[position], clickListener, position)
    }

    fun setDataList(data: List<String>) {
        dataList.clear()
        dataList.addAll(data)
    }
}