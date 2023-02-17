package com.identity.drraanka.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.identity.drraanka.R
import com.identity.drraanka.data.remote.model.Scheme
import com.identity.drraanka.databinding.LayoutSelectSchemeItemBinding

class SelectSchemeAdapter(private val context: Context, private val clickListener: (Scheme?) -> Unit) :
    RecyclerView.Adapter<SelectSchemeAdapter.SelectSchemeViewHolder>() {

    private val dataList = ArrayList<Scheme?>()

    class SelectSchemeViewHolder(private val binding: LayoutSelectSchemeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Scheme?, clickListener: (Scheme?) -> Unit, context: Context) {
            binding.tvSchemeName.text = data?.schemeName
            binding.tvMinContributionAmount.text = data?.minimumContribution
            binding.tvSchemeTenureMonths.text = data?.tenture
            binding.tvRaankaContributionAmount.text = data?.raankaContribution
            binding.tvProductValueAmount.text = data?.productValue

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

    fun setDataList(data: List<Scheme?>) {
        dataList.clear()
        dataList.addAll(data)
    }
}