package com.identity.drraanka.ui.registration

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.identity.drraanka.MainActivity
import com.identity.drraanka.databinding.FragmentRegistrationBinding
import com.identity.drraanka.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private val viewModel by viewModels<RegistrationViewModel>()
    private var dialog: Dialog? = null
    private lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).hideBottomNavigationBar()
        (activity as MainActivity).hideActionBar()
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(com.identity.drraanka.R.layout.progress)
        dialog = builder.create()

        binding.apply {
            btnRegistrationCreate.setOnClickListener {
                if(etvRegistrationName.text.isNullOrBlank()){
                    etvRegistrationName.error = "Enter Name"
                    etvRegistrationName.requestFocus()
                    return@setOnClickListener
                }

                if(etvRegistrationMobile.text.isNullOrBlank()){
                    etvRegistrationMobile.error = "Enter Mobile Number"
                    etvRegistrationMobile.requestFocus()
                    return@setOnClickListener
                }

                if(etvRegistrationPassword.text.isNullOrBlank()){
                    etvRegistrationPassword.error = "Enter Password"
                    etvRegistrationPassword.requestFocus()
                    return@setOnClickListener
                }

                if(etvRegistrationMail.text.isNullOrBlank()){
                    etvRegistrationMail.error = "Enter Mail id"
                    etvRegistrationMail.requestFocus()
                    return@setOnClickListener
                }

                if(etvRegistrationAddress.text.isNullOrBlank()){
                    etvRegistrationAddress.error = "Enter Address"
                    etvRegistrationAddress.requestFocus()
                    return@setOnClickListener
                }

                if(etvRegistrationAadhar.text.isNullOrBlank()){
                    etvRegistrationAadhar.error = "Enter Aadhar No."
                    etvRegistrationAadhar.requestFocus()
                    return@setOnClickListener
                }

                val params = HashMap<String,String>()
                params["firstname"] = etvRegistrationName.text.toString()
                params["address"] = etvRegistrationAddress.text.toString()
                params["mobileno"] = etvRegistrationMobile.text.toString()
                params["password"] = etvRegistrationPassword.text.toString()
                params["email"] = etvRegistrationMail.text.toString()
                params["Aadhar"] = etvRegistrationAadhar.text.toString()

                viewModel.registerUser(params)
                setUpObserver()
            }
        }

        return binding.root
    }

    private fun setUpObserver() {
        viewModel.registrationResponse.observe(viewLifecycleOwner){
            when(it.status){
                Status.LOADING->{
                    setDialog(true)
                }
                Status.SUCCESS->{
                    setDialog(false)
                    if(!it.data!!.error!!){
                        Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    }else{
                        Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT).show()
                    }
                }
                Status.ERROR->{
                    setDialog(false)
                }
            }
        }
    }

    private fun setDialog(show: Boolean) {
        if (show) dialog!!.show() else dialog!!.dismiss()
    }
}