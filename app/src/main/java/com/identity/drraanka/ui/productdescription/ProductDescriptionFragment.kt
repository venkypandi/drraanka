package com.identity.drraanka.ui.productdescription

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.databinding.FragmentProductDescriptionBinding
import com.identity.drraanka.ui.adapter.ProductImageAdapter

class ProductDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentProductDescriptionBinding
    private lateinit var adapter: ProductImageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).setUpActionBar(
            title = getString(R.string.products),
            backPressedListener = {this.requireActivity().onBackPressed()}
        )
        binding = FragmentProductDescriptionBinding.inflate(inflater, container, false)
        setRecyclerViewAdapter()
        return binding.root
    }

    private fun setRecyclerViewAdapter() {
        adapter = ProductImageAdapter(requireActivity()) { data: Int -> clickAction(data) }
        binding.recyclerViewProducts.adapter = adapter
        adapter.setDataList(listOf(1,2,3,4))
        adapter.notifyDataSetChanged()
        if (adapter.itemCount >= 1) {
            binding.recyclerViewProducts.visibility = View.VISIBLE
            binding.tvProductImgError.visibility = View.GONE

        } else {
            binding.recyclerViewProducts.visibility = View.GONE
            binding.tvProductImgError.visibility = View.VISIBLE
            binding.tvProductImgError.text = "No Image found"
        }
    }

    private fun clickAction(data: Int) {
        binding.ivProductImg.setImageDrawable(null)
        binding.ivProductImg.setImageDrawable(ContextCompat.getDrawable(requireActivity(), data))
    }
}