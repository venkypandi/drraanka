package com.identity.drraanka.ui.categories

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
import com.bumptech.glide.Glide
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.data.remote.model.Product
import com.identity.drraanka.data.remote.model.ProductCategory
import com.identity.drraanka.databinding.FragmentCategoriesBinding
import com.identity.drraanka.ui.adapter.CategoriesAdapter
import com.identity.drraanka.ui.adapter.FilterAdapter
import com.identity.drraanka.ui.adapter.ImageCarouselAdapter
import com.identity.drraanka.ui.dashboard.DashboardFragmentDirections
import com.identity.drraanka.ui.dashboard.DashboardViewModel
import com.identity.drraanka.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var adapter: CategoriesAdapter
    private val viewModel by viewModels<DashboardViewModel>()
    private var dialog: Dialog? = null

    private lateinit var filterAdapter: FilterAdapter
    private var dataMap = HashMap<String,Boolean>()
    private var productList = ArrayList<Product>()
    private var productCategoryList = ArrayList<ProductCategory>()


//    private var dataMap = hashMapOf("All" to true, "Aspire-1" to false, "Aspire-2" to false, "Aspire-3" to false, "Aspire-4" to false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).setUpActionBar(
            title = getString(R.string.categories),
            backPressedListener = {this.requireActivity().onBackPressed()},
            { val directions = CategoriesFragmentDirections.actionCategoriesFragmentToProfileFragment()
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
                    val directions = CategoriesFragmentDirections.actionCategoriesFragmentToLoginFragment()
                    findNavController().navigate(directions)

                }
                logoutBuilder.setNegativeButton("No") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                val logoutAlert = logoutBuilder.create()
                logoutAlert.show()
            }
        )
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(com.identity.drraanka.R.layout.progress)
        dialog = builder.create()

        val params = HashMap<String,String>()
        params["version_code"] = "1"
        params["os_type"] = "android"

        viewModel.getAppConfig(params)

        viewModel.appConfigValue.observe(viewLifecycleOwner) { it ->

            when (it.status) {
                Status.LOADING -> {
                    setDialog(true)
                }
                Status.SUCCESS -> {
                    if (!it.data!!.error!!) {
                        productList.clear()
                        productCategoryList.clear()
                        dataMap["All"] = true
                        productCategoryList.addAll(it.data.productCategoryList)
                        it.data.productCategoryList.forEach { list->
                            dataMap[list.categoryName!!] = false
                            list.productList?.forEach { product ->
                                if (product != null) {
                                    productList.add(product)
                                }
                            }
                        }
                        setFilterAdapter()
                        setCategoriesAdapter(productList)

                    } else {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
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
//        setFilterAdapter()
//        setCategoriesAdapter()
        return binding.root
    }

    private fun setFilterAdapter() {
        filterAdapter = FilterAdapter(requireActivity()) { key, value ->
            filterItemClickAction(key, value)
        }
        binding.recyclerViewFilter.adapter = filterAdapter
        filterAdapter.setDataList(dataMap)
        filterAdapter.notifyDataSetChanged()
        if (filterAdapter.itemCount >= 1) {
            binding.recyclerViewFilter.visibility = View.VISIBLE
        } else {
            binding.recyclerViewCategories.visibility = View.GONE
        }
    }

    private fun filterItemClickAction(key: String, value: Int) {
        var tempList = arrayListOf<Product>()
        dataMap.forEach {
            dataMap[it.key] = false
        }
        dataMap[key] = true
        filterAdapter.setDataList(dataMap)
        if(key == "All"){
            productCategoryList.forEach{
                it.productList?.forEach {
                    if (it != null) {
                        tempList.add(it)
                    }
                }
            }
        } else {
            productCategoryList.forEach {

                if (it.categoryName == key) {
                    it.productList?.forEach {
                        if (it != null) {
                            tempList.add(it)
                        }
                    }
                }
            }
        }
        setCategoriesAdapter(tempList)
        filterAdapter.notifyDataSetChanged()
    }

    private fun setCategoriesAdapter(list:List<Product>) {
        adapter = CategoriesAdapter(requireActivity()) {
            listItemClicked(it)
        }
        binding.recyclerViewCategories.adapter = adapter
        adapter.setDataList(list)
        adapter.notifyDataSetChanged()
        if (adapter.itemCount >= 1) {
            binding.recyclerViewCategories.visibility = View.VISIBLE
            binding.tvError.visibility = View.GONE
            binding.clProgressBar.visibility = View.GONE
        } else {
            binding.recyclerViewCategories.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = "No Products found"
            binding.clProgressBar.visibility = View.GONE
        }
    }

    private fun listItemClicked(product: Product) {
        val  directions = CategoriesFragmentDirections.actionCategoriesFragmentToProductDescriptionFragment(
            product = product
        )
        findNavController().navigate(directions)
    }

    private fun setDialog(show: Boolean) {
        if (show) dialog!!.show() else dialog!!.dismiss()
    }
}