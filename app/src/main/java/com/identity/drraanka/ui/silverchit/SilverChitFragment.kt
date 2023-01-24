package com.identity.drraanka.ui.silverchit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.identity.drraanka.MainActivity
import com.identity.drraanka.databinding.FragmentSilverChitBinding


class SilverChitFragment : Fragment() {

    private lateinit var binding: FragmentSilverChitBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        binding = FragmentSilverChitBinding.inflate(inflater, container, false)
        return binding.root
    }

}