package com.identity.drraanka.ui.silverchit

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
import com.identity.drraanka.cache.AppCache
import com.identity.drraanka.data.remote.model.CustomerChit
import com.identity.drraanka.databinding.FragmentSilverChitBinding
import com.identity.drraanka.ui.adapter.ImageCarouselAdapter
import com.identity.drraanka.ui.adapter.SilverChitAdapter
import com.identity.drraanka.ui.dashboard.DashboardFragmentDirections
import com.identity.drraanka.ui.dashboard.DashboardViewModel
import com.identity.drraanka.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SilverChitFragment : Fragment() {

    private lateinit var binding: FragmentSilverChitBinding
    private lateinit var adapter: SilverChitAdapter
    private var dialog: Dialog? = null
    private val viewModel by viewModels<DashboardViewModel>()
    private val args by navArgs<SilverChitFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).setUpActionBar(
            title = getString(R.string.my_plans),
            backPressedListener = {this.requireActivity().onBackPressed()},
             directionListener = {
                 val directions = SilverChitFragmentDirections.actionSilverChitFragmentToProfileFragment()
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
                    val directions = SilverChitFragmentDirections.actionSilverChitFragmentToLoginFragment()
                    findNavController().navigate(directions)

                }
                logoutBuilder.setNegativeButton("No") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                val logoutAlert = logoutBuilder.create()
                logoutAlert.show()
            }
        )


        binding = FragmentSilverChitBinding.inflate(inflater, container, false)

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(com.identity.drraanka.R.layout.progress)
        dialog = builder.create()

        val params = HashMap<String,String>()
        params["limit_per_page"] = "5"
        params["next_page_offset"] = "1"
        params["session_id"] = AppCache.sessionId.toString()

        viewModel.getMySchemes(params)


        viewModel.mySchemeValue.observe(viewLifecycleOwner){
            when (it.status) {
                Status.LOADING -> {
                    setDialog(true)
                }
                Status.SUCCESS -> {
                    if (!it.data!!.error!!) {
                        setRecyclerViewAdapter(it.data.customerChitList!!)

                    } else {
                        Toast.makeText(requireContext(), it.data.message.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                    setDialog(false)

                }
                Status.ERROR -> {
                    setDialog(false)
                    Toast.makeText(requireContext(), "Error Fetching image", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }


        binding.ivAddScheme.setOnClickListener {
            val directions = SilverChitFragmentDirections.actionSilverChitFragmentToSelectSchemeFragment(
                sessionId = AppCache.sessionId

            )
            findNavController().navigate(directions)

        }
        return binding.root
    }

    private fun setRecyclerViewAdapter(list: List<CustomerChit?>) {
        adapter = SilverChitAdapter { data: CustomerChit? ->
            if (data != null) {
                clickAction(data)
            }
        }
        binding.recyclerViewSilverChit.adapter = adapter
        adapter.setDataList(list)
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


    private fun clickAction(data: CustomerChit) {
        val directions = SilverChitFragmentDirections.actionSilverChitFragmentToSchemeDetailsFragment(
            sessionId = AppCache.sessionId,
            branchId = data.branchId,
            chitCode = data.chitCode,
            tenure = data.tenure, schemeName = data.chitName, contribution = data.contribution, startDate = data.chitStartDate,

        )
        findNavController().navigate(directions)
    }

    private fun setDialog(show: Boolean) {
        if (show) dialog!!.show() else dialog!!.dismiss()
    }
}