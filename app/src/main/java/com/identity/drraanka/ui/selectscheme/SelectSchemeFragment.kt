package com.identity.drraanka.ui.selectscheme

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.data.remote.model.Scheme
import com.identity.drraanka.databinding.FragmentSelectSchemeBinding
import com.identity.drraanka.ui.adapter.ImageCarouselAdapter
import com.identity.drraanka.ui.adapter.SelectSchemeAdapter
import com.identity.drraanka.ui.dashboard.DashboardFragmentDirections
import com.identity.drraanka.ui.dashboard.DashboardViewModel
import com.identity.drraanka.ui.silverchit.SilverChitFragmentArgs
import com.identity.drraanka.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class SelectSchemeFragment : Fragment() {

    private lateinit var binding: FragmentSelectSchemeBinding
    private lateinit var adapter: SelectSchemeAdapter
    private var dialog: Dialog? = null
    private val args by navArgs<SelectSchemeFragmentArgs>()


    private val viewModel by viewModels<DashboardViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).setUpActionBar(
            title = getString(R.string.select_scheme),
            backPressedListener = {this.requireActivity().onBackPressed()},
            {
                val directions =SelectSchemeFragmentDirections.actionSelectSchemeFragmentToProfileFragment()
                findNavController().navigate(directions)
            },
            logoutListener = {
                val logoutBuilder = AlertDialog.Builder(requireContext())
                logoutBuilder.setTitle("Log Out")
                logoutBuilder.setMessage("Are you sure want to log out?")
                logoutBuilder.setCancelable(false)
                logoutBuilder.setPositiveButton("Yes") { dialogInterface, i ->
                    val preferences = activity?.getSharedPreferences("MY_PREFS",
                        Context.MODE_PRIVATE
                    )
                    val editor: SharedPreferences.Editor = preferences!!.edit()
                    editor.clear()
                    editor.apply()
                    val directions = SelectSchemeFragmentDirections.actionSelectSchemeFragmentToLoginFragment()
                    findNavController().navigate(directions)

                }
                logoutBuilder.setNegativeButton("No") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                val logoutAlert = logoutBuilder.create()
                logoutAlert.show()
            }
        )
        binding = FragmentSelectSchemeBinding.inflate(inflater, container, false)

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(com.identity.drraanka.R.layout.progress)
        dialog = builder.create()

        val params = HashMap<String,String>()
        params["version_code"] = "1"
        params["os_type"] = "android"

        viewModel.getAppConfig(params)

        viewModel.appConfigValue.observe(viewLifecycleOwner) {

            when (it.status) {
                Status.LOADING -> {
                    setDialog(true)
                }
                Status.SUCCESS -> {
                    if (!it.data!!.error!!) {
                        setRecyclerViewAdapter(it.data.schemeList!!)

                    } else {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    setDialog(false)

                }
                Status.ERROR -> {
                    setDialog(false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }

        return binding.root
    }

    private fun setRecyclerViewAdapter(list: List<Scheme?>) {
        adapter = SelectSchemeAdapter(requireActivity()) { data: Scheme? -> clickAction(data) }
        binding.recyclerViewSelectScheme.adapter = adapter
        adapter.setDataList(list)
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

    private fun clickAction(data: Scheme?) {
        data?.apply {
            val params = HashMap<String,String>()
            params["branch_id"] = branchId.toString()
            params["chit_code"] = chitCode!!
            params["customer_id"] = args.sessionId!!
            params["chit_scheme_month"] = tenture!!
            params["chit_desc"] = schemeName!!
            params["chit_amount"] = minimumContribution!!
            params["r_contribution"] = raankaContribution!!
            params["chit_start_date"] = ""
            params["total_amount"] = productValue!!
            params["chit_end_date"] = ""
            params["chit_status"] = ""

            viewModel.addChit(params)

            viewModel.addChitResponse.observe(viewLifecycleOwner){
                when (it.status) {
                    Status.LOADING -> {
                        setDialog(true)
                    }
                    Status.SUCCESS -> {
                        if (!it.data!!.error!!) {
                            findNavController().navigateUp()
                        } else {
                            Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        setDialog(false)

                    }
                    Status.ERROR -> {
                        setDialog(false)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }
        }




    }

    private fun setDialog(show: Boolean) {
        if (show) dialog!!.show() else dialog!!.dismiss()
    }

}