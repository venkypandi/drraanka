package com.identity.drraanka.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).setUpActionBar(
            title = getString(R.string.profile),
            backPressedListener = { this.requireActivity().onBackPressed() }
        )
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
}