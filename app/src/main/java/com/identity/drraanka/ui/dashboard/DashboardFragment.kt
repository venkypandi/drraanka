package com.identity.drraanka.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.identity.drraanka.MainActivity
import com.identity.drraanka.R
import com.identity.drraanka.databinding.FragmentDashboardBinding
import com.identity.drraanka.ui.adapter.ImageCarouselAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var imageCarouselAdapter: ImageCarouselAdapter
    private var dots: ArrayList<ImageView> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (activity as MainActivity).showBottomNavigationBar()
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        imageCarouselAdapter =
            ImageCarouselAdapter(listOf(R.drawable.sample1, R.drawable.sample2, R.drawable.sample3))
        binding.viewPagerImage.adapter = imageCarouselAdapter

        setImageDots(imageCarouselAdapter.count)
        setUpViewPageListener()

        binding.ivFirstImage.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.sample1))
        binding.ivSecondImage.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.sample2))

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
}