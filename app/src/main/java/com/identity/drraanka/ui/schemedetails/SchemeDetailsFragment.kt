package com.identity.drraanka.ui.schemedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.databinding.FragmentSchemeDetailsBinding
import com.identity.drraanka.ui.adapter.SchemeDetailsAdapter

class SchemeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSchemeDetailsBinding
    private lateinit var adapter: SchemeDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).setUpActionBar(
            title = getString(R.string.scheme_details),
            backPressedListener = { this.requireActivity().onBackPressed() }
        )
        binding = FragmentSchemeDetailsBinding.inflate(inflater, container, false)
        setRecyclerViewAdapter()
        return binding.root
    }

    private fun setRecyclerViewAdapter() {
        adapter = SchemeDetailsAdapter { data: String -> clickAction(data) }
        binding.recyclerViewSchemeDetails.adapter = adapter
        adapter.setDataList(listOf("Aspire-1", "Aspire-2", "Aspire-3", "Aspire-4"))
        adapter.notifyDataSetChanged()
        if (adapter.itemCount >= 1) {
            binding.recyclerViewSchemeDetails.visibility = View.VISIBLE
            binding.tvSilverChitError.visibility = View.GONE
            binding.clProgressBar.visibility = View.GONE
        } else {
            binding.recyclerViewSchemeDetails.visibility = View.GONE
            binding.tvSilverChitError.visibility = View.VISIBLE
            binding.tvSilverChitError.text = "No scheme found"
            binding.clProgressBar.visibility = View.GONE
        }
    }

    private fun clickAction(data: String) {
        Toast.makeText(requireActivity(), data, Toast.LENGTH_SHORT).show()
    }

}