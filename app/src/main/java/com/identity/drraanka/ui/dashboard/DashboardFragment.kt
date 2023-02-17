package com.identity.drraanka.ui.dashboard

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.cache.AppCache
import com.identity.drraanka.databinding.FragmentDashboardBinding
import com.identity.drraanka.ui.adapter.ImageCarouselAdapter
import com.identity.drraanka.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private var dialog: Dialog? = null
    private lateinit var imageCarouselAdapter: ImageCarouselAdapter
    private var dots: ArrayList<ImageView> = ArrayList()
    private val viewModel by viewModels<DashboardViewModel>()
    private val args by navArgs<DashboardFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        (activity as MainActivity).hideActionBar()
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(com.identity.drraanka.R.layout.progress)
        dialog = builder.create()

        val params = HashMap<String,String>()
        params["version_code"] = "1"
        params["os_type"] = "android"

        viewModel.getAppConfig(params)

        binding.ivProfileIcon.setOnClickListener {
            val directions = DashboardFragmentDirections.actionDashboardFragmentToProfileFragment()
            findNavController().navigate(directions)
        }

        binding.ivLogOut.setOnClickListener {
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
                val directions = DashboardFragmentDirections.actionDashboardFragmentToLoginFragment()
                findNavController().navigate(directions)

            }
            logoutBuilder.setNegativeButton("No") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            val logoutAlert = logoutBuilder.create()
            logoutAlert.show()
        }

        viewModel.appConfigValue.observe(viewLifecycleOwner) {

            when (it.status) {
                Status.LOADING -> {
                    setDialog(true)
                }
                Status.SUCCESS -> {
                    if (!it.data!!.error!!) {
                        imageCarouselAdapter =
                            ImageCarouselAdapter(it.data.bannerImagesArrayObject!!,requireContext())
                        binding.viewPagerImage.adapter = imageCarouselAdapter

                        setImageDots(imageCarouselAdapter.count)
                        setUpViewPageListener()

                        binding.apply {
                            tvGoldRateValue.text = it.data.liveRates?.gold
                            tvSilverRateValue.text = it.data.liveRates?.silver
                        }

                        Glide.with(requireContext())
                            .load(it.data.dynamicAdBanner?.get(0))
                            .error(R.drawable.alert)
                            .into(binding.ivFirstImage)

                        Glide.with(requireContext())
                            .load(it.data.dynamicAdBanner?.get(1))
                            .error(R.drawable.alert)
                            .into(binding.ivSecondImage)

//                        binding.ivFirstImage.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.sample1))
//                        binding.ivSecondImage.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.sample2))

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


        binding.ivBtnSilverChit.setOnClickListener {
            val directions = DashboardFragmentDirections.actionDashboardFragmentToSilverChitFragment(
                sessionId = AppCache.sessionId
            )
            findNavController().navigate(directions)
        }

        binding.ivBtnProductCatalog.setOnClickListener {
            val directions = DashboardFragmentDirections.actionDashboardFragmentToCategoriesFragment()
            findNavController().navigate(directions)
        }
        return binding.root
    }

    private fun setImageDots(count: Int) {
        dots = ArrayList(count)
        for (i in 0 until count) {
            dots.add(i,ImageView(requireActivity()))
            dots[i].adjustViewBounds = true
            dots[i].scaleType = ImageView.ScaleType.CENTER_CROP
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.dot_small))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            when (i) {
                0 -> params.setMargins(0,0,1,0)
                count-1 -> params.setMargins(1,0,0,0)
                else -> params.setMargins(0,0,0,0)
            }
            binding.llSliderDots.addView(dots[i], params)
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.dot_large))
    }

    private fun setUpViewPageListener() {
        binding.viewPagerImage.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

            override fun onPageSelected(position: Int) {
                for (i in 0 until imageCarouselAdapter.count) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.dot_small))
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.dot_large))
            }

            override fun onPageScrollStateChanged(state: Int) { }
        })

    }

    private fun setDialog(show: Boolean) {
        if (show) dialog!!.show() else dialog!!.dismiss()
    }
}