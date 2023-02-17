package com.identity.drraanka.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.identity.drraanka.R
import com.identity.drraanka.data.remote.model.BannerImagesArrayObject
import com.identity.drraanka.databinding.LayoutImageCarouselBinding

class ImageCarouselAdapter(private val imageList: List<BannerImagesArrayObject>, private val context:Context): PagerAdapter() {

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, viewObject: Any): Boolean {
        return view == viewObject
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = LayoutImageCarouselBinding.inflate(LayoutInflater.from(container.context), container,false)
//        binding.ivCarousel.setImageResource(imageList[position])

        Glide.with(context)
            .load(imageList[position].image)
            .error(R.drawable.alert)
            .into(binding.ivCarousel)
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, viewObject: Any) {
        container.removeView(viewObject as View)
    }
}