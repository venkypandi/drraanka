package com.identity.drraanka.ui.productdescription

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.data.remote.model.Attachment
import com.identity.drraanka.data.remote.model.Product
import com.identity.drraanka.databinding.FragmentProductDescriptionBinding
import com.identity.drraanka.ui.adapter.ProductImageAdapter
import com.identity.drraanka.ui.dashboard.DashboardFragmentDirections
import com.identity.drraanka.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDescriptionFragment : Fragment() {

    private lateinit var binding: FragmentProductDescriptionBinding
    private lateinit var adapter: ProductImageAdapter
    private val viewModel by viewModels<DashboardViewModel>()
    private var dialog: Dialog? = null
    private val args by navArgs<ProductDescriptionFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).setUpActionBar(
            title = getString(R.string.products),
            backPressedListener = {this.requireActivity().onBackPressed()},
            directionListener = {   val directions = ProductDescriptionFragmentDirections.actionProductDescriptionFragmentToProfileFragment()
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
                    val directions = ProductDescriptionFragmentDirections.actionProductDescriptionFragmentToLoginFragment()
                    findNavController().navigate(directions)

                }
                logoutBuilder.setNegativeButton("No") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                val logoutAlert = logoutBuilder.create()
                logoutAlert.show()
            }
        )
        binding = FragmentProductDescriptionBinding.inflate(inflater, container, false)

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(com.identity.drraanka.R.layout.progress)
        dialog = builder.create()
        binding.apply {
            tvProductDescription.text = "${args.product?.productDesc1}\n" +
                    "${args.product?.productDesc2}\n" +
                    "${args.product?.productDesc3}\n" +
                    "${args.product?.productDesc4}\n" +
                    "${args.product?.productDesc5}\n" +
                    "Product Code:${args.product?.productCode}/ Metal:${args.product?.productMetal}/ Weight:${args.product?.productWeight}\n" ?: "NA"
            tvProductName.text = args.product?.productName ?: "NA"
            Glide.with(requireContext())
                .load(args.product?.productImages?.attachmentList?.get(0)?.image)
                .error(R.drawable.alert)
                .into(ivProductImg)
        }
        setRecyclerViewAdapter(args.product?.productImages?.attachmentList)
        return binding.root
    }

    private fun setRecyclerViewAdapter(list:List<Attachment?>?) {
        adapter = ProductImageAdapter(requireActivity()) { data: Attachment? -> clickAction(data) }
        binding.recyclerViewProducts.adapter = adapter
        adapter.setDataList(list!!)
        adapter.notifyDataSetChanged()
        if (adapter.itemCount >= 1) {
            binding.recyclerViewProducts.visibility = View.VISIBLE
            binding.tvProductImgError.visibility = View.GONE

        } else {
            binding.recyclerViewProducts.visibility = View.GONE
            binding.tvProductImgError.visibility = View.VISIBLE
            binding.tvProductImgError.text = "No Image found"
        }
    }

    private fun clickAction(data: Attachment?) {
        Glide.with(requireContext())
            .load(data?.image)
            .error(R.drawable.alert)
            .into(binding.ivProductImg)
    }
}