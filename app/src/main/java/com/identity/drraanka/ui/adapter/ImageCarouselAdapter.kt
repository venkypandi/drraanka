package com.identity.drraanka.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.identity.drraanka.databinding.LayoutImageCarouselBinding

class ImageCarouselAdapter(private val imageList: List<Int>): PagerAdapter() {

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, viewObject: Any): Boolean {
        return view == viewObject
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = LayoutImageCarouselBinding.inflate(LayoutInflater.from(container.context), container,false)
        binding.ivCarousel.setImageResource(imageList[position])
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, viewObject: Any) {
        container.removeView(viewObject as View)
    }
}