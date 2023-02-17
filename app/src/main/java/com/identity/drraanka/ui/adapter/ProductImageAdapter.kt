package com.identity.drraanka.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.identity.drraanka.R
import com.identity.drraanka.data.remote.model.Attachment
import com.identity.drraanka.databinding.LayoutProductItemBinding


class ProductImageAdapter(
    private val context: Context,
    private val clickListener: (Attachment?) -> Unit
) : RecyclerView.Adapter<ProductImageAdapter.ProductImageViewHolder>() {

    private val dataList = ArrayList<Attachment?>()

    inner class ProductImageViewHolder(private val binding: LayoutProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Attachment?, clickListener: (Attachment?) -> Unit, context: Context) {
            Glide.with(context)
                .load(data?.image)
                .error(R.drawable.alert)
                .into(binding.ivProduct)

            binding.mcViewProduct.setOnClickListener {
                clickListener(data)
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

    fun setDataList(data: List<Attachment?>) {
        dataList.clear()
        dataList.addAll(data)
    }
}