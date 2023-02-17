package com.identity.drraanka.ui.schemedetails

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.data.remote.model.CustomerChitDetails
import com.identity.drraanka.data.remote.model.Scheme
import com.identity.drraanka.databinding.FragmentSchemeDetailsBinding
import com.identity.drraanka.ui.adapter.SchemeDetailsAdapter
import com.identity.drraanka.ui.dashboard.DashboardFragmentDirections
import com.identity.drraanka.ui.dashboard.DashboardViewModel
import com.identity.drraanka.ui.selectscheme.SelectSchemeFragmentArgs
import com.identity.drraanka.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchemeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSchemeDetailsBinding
    private lateinit var adapter: SchemeDetailsAdapter

    private var dialog: Dialog? = null
    private val args by navArgs<SchemeDetailsFragmentArgs>()


    private val viewModel by viewModels<DashboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).setUpActionBar(
            title = getString(R.string.scheme_details),
            backPressedListener = { this.requireActivity().onBackPressed() },
            {
                val directions = SchemeDetailsFragmentDirections.actionSchemeDetailsFragmentToProfileFragment()
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
                    val directions = SchemeDetailsFragmentDirections.actionSchemeDetailsFragmentToLoginFragment()
                    findNavController().navigate(directions)

                }
                logoutBuilder.setNegativeButton("No") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                val logoutAlert = logoutBuilder.create()
                logoutAlert.show()
            }
        )
        binding = FragmentSchemeDetailsBinding.inflate(inflater, container, false)
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(com.identity.drraanka.R.layout.progress)
        dialog = builder.create()

        binding.apply {

            tvContributionAmount.text = args.contribution.toString()
            tvSchemeName.text = args.schemeName
            tvSchemeDateValue.text = args.startDate

            val params = HashMap<String,String>()
            params["limit_per_page"] = "5"
            params["next_page_offset"] = "1"
            params["session_id"] = args.sessionId!!
            params["branch_Id"] = args.branchId!!
            params["chit_code"] = args.chitCode!!
            params["chit_tenture"] = args.tenure!!

            viewModel.getChitDetails(params)

            viewModel.chitDetailsResponse.observe(viewLifecycleOwner){
                when (it.status) {
                    Status.LOADING -> {
                        setDialog(true)
                    }
                    Status.SUCCESS -> {
                        if (!it.data!!.error!!) {

                            it.data.customerChitListDetails?.let { it1 -> setRecyclerViewAdapter(it1) }


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

        }
//        setRecyclerViewAdapter()
        return binding.root
    }

    private fun setRecyclerViewAdapter(list: List<CustomerChitDetails?>) {
        adapter = SchemeDetailsAdapter { data: CustomerChitDetails? -> clickAction(data) }
        binding.recyclerViewSchemeDetails.adapter = adapter
        adapter.setDataList(list)
        adapter.notifyDataSetChanged()
        if (adapter.itemCount >= 1) {
            binding.recyclerViewSchemeDetails.visibility = View.VISIBLE
            binding.tvError.visibility = View.GONE
            binding.clProgressBar.visibility = View.GONE
        } else {
            binding.recyclerViewSchemeDetails.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = "No scheme found"
            binding.clProgressBar.visibility = View.GONE
        }
    }

    private fun clickAction(data: CustomerChitDetails?) {
        Toast.makeText(requireActivity(), data?.chitPayAmount, Toast.LENGTH_SHORT).show()
    }

    private fun setDialog(show: Boolean) {
        if (show) dialog!!.show() else dialog!!.dismiss()
    }

}