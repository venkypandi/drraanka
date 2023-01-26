package com.identity.drraanka.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.identity.drraanka.R
import com.identity.drraanka.databinding.LayoutSelectSchemeItemBinding

class SelectSchemeAdapter(private val context: Context, private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<SelectSchemeAdapter.SelectSchemeViewHolder>() {

    private val dataList = ArrayList<String>()

    class SelectSchemeViewHolder(private val binding: LayoutSelectSchemeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String, clickListener: (String) -> Unit, context: Context) {
            binding.tvSchemeName.text = data
            binding.tvMinContributionAmount.text = context.getString(R.string.rs)+" 1000.00"
            binding.tvSchemeTenureMonths.text = "6 Months"
            binding.tvRaankaContributionAmount.text = context.getString(R.string.rs)+" 1000.00"
            binding.tvProductValueAmount.text = context.getString(R.string.rs)+" 10000.00"

            binding.ivAddScheme.setOnClickListener {
                clickListener(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectSchemeViewHolder {
        val binding = LayoutSelectSchemeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SelectSchemeViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: SelectSchemeViewHolder, position: Int) {
        holder.bind(dataList[position], clickListener, context)
    }

    fun setDataList(data: List<String>) {
        dataList.clear()
        dataList.addAll(data)
    }
}