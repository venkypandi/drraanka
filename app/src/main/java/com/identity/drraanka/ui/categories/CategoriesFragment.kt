package com.identity.drraanka.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.databinding.FragmentCategoriesBinding
import com.identity.drraanka.ui.adapter.CategoriesAdapter
import com.identity.drraanka.ui.adapter.FilterAdapter

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var adapter: CategoriesAdapter
    private lateinit var filterAdapter: FilterAdapter
    private var dataMap = hashMapOf("All" to true, "Aspire-1" to false, "Aspire-2" to false, "Aspire-3" to false, "Aspire-4" to false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).setUpActionBar(
            title = getString(R.string.categories),
            backPressedListener = {this.requireActivity().onBackPressed()}
        )
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        setFilterAdapter()
        setCategoriesAdapter()
        return binding.root
    }

    private fun setFilterAdapter() {
        filterAdapter = FilterAdapter(requireActivity()) { key, value ->
            filterItemClickAction(key, value)
        }
        binding.recyclerViewFilter.adapter = filterAdapter
        filterAdapter.setDataList(dataMap)
        filterAdapter.notifyDataSetChanged()
        if (filterAdapter.itemCount >= 1) {
            binding.recyclerViewFilter.visibility = View.VISIBLE
        } else {
            binding.recyclerViewCategories.visibility = View.GONE
        }
    }

    private fun filterItemClickAction(key: String, value: Int) {
        dataMap.forEach {
            dataMap[it.key] = false
        }
        dataMap[key] = true
        filterAdapter.setDataList(dataMap)
        filterAdapter.notifyDataSetChanged()
    }

    private fun setCategoriesAdapter() {
        adapter = CategoriesAdapter(requireActivity()) {
            listItemClicked()
        }
        binding.recyclerViewCategories.adapter = adapter
        adapter.setDataList(listOf("Aspire-1", "Aspire-2", "Aspire-3", "Aspire-4"))
        adapter.notifyDataSetChanged()
        if (adapter.itemCount >= 1) {
            binding.recyclerViewCategories.visibility = View.VISIBLE
            binding.tvError.visibility = View.GONE
            binding.clProgressBar.visibility = View.GONE
        } else {
            binding.recyclerViewCategories.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = "No categories found"
            binding.clProgressBar.visibility = View.GONE
        }
    }

    private fun listItemClicked() {
        findNavController().navigate(R.id.action_categoriesFragment_to_productDescriptionFragment)
    }
}