package com.identity.drraanka.ui.selectscheme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.databinding.FragmentSelectSchemeBinding
import com.identity.drraanka.ui.adapter.SelectSchemeAdapter

class SelectSchemeFragment : Fragment() {

    private lateinit var binding: FragmentSelectSchemeBinding
    private lateinit var adapter: SelectSchemeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).setUpActionBar(
            title = getString(R.string.select_scheme),
            backPressedListener = {this.requireActivity().onBackPressed()}
        )
        binding = FragmentSelectSchemeBinding.inflate(inflater, container, false)
        setRecyclerViewAdapter()
        return binding.root
    }

    private fun setRecyclerViewAdapter() {
        adapter = SelectSchemeAdapter(requireActivity()) { data: String -> clickAction(data) }
        binding.recyclerViewSelectScheme.adapter = adapter
        adapter.setDataList(listOf("Aspire-1", "Aspire-2", "Aspire-3", "Aspire-4"))
        adapter.notifyDataSetChanged()
        if (adapter.itemCount >= 1) {
            binding.recyclerViewSelectScheme.visibility = View.VISIBLE
            binding.tvError.visibility = View.GONE
            binding.clProgressBar.visibility = View.GONE
        } else {
            binding.recyclerViewSelectScheme.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = "No scheme found"
            binding.clProgressBar.visibility = View.GONE
        }
    }

    private fun clickAction(data: String) {
        Toast.makeText(requireActivity(), data, Toast.LENGTH_SHORT).show()
    }

}