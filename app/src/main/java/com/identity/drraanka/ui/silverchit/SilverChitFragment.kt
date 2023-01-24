package com.identity.drraanka.ui.silverchit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.databinding.FragmentSilverChitBinding
import com.identity.drraanka.ui.adapter.SilverChitAdapter


class SilverChitFragment : Fragment() {

    private lateinit var binding: FragmentSilverChitBinding
    private lateinit var adapter: SilverChitAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).setUpActionBar(
            title = getString(R.string.my_plans),
            backPressedListener = {this.requireActivity().onBackPressed()}
        )
        binding = FragmentSilverChitBinding.inflate(inflater, container, false)
        setRecyclerViewAdapter()
        return binding.root
    }

    private fun setRecyclerViewAdapter() {
        adapter = SilverChitAdapter { data: String -> clickAction(data) }
        binding.recyclerViewSilverChit.adapter = adapter
        adapter.setDataList(listOf("Aspire-1", "Aspire-2", "Aspire-3", "Aspire-4"))
        adapter.notifyDataSetChanged()
        if (adapter.itemCount >= 1) {
            binding.recyclerViewSilverChit.visibility = View.VISIBLE
            binding.tvSilverChitError.visibility = View.GONE
            binding.clProgressBar.visibility = View.GONE
        } else {
            binding.recyclerViewSilverChit.visibility = View.GONE
            binding.tvSilverChitError.visibility = View.VISIBLE
            binding.tvSilverChitError.text = "No scheme found"
            binding.clProgressBar.visibility = View.GONE
        }
    }


    private fun clickAction(data: String) {
        Toast.makeText(requireActivity(), data, Toast.LENGTH_SHORT).show()
    }
}