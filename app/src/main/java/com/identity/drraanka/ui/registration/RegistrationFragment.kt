package com.identity.drraanka.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.identity.drraanka.MainActivity
import com.identity.drraanka.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).hideBottomNavigationBar()
        (activity as MainActivity).hideActionBar()
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        return binding.root
    }
}