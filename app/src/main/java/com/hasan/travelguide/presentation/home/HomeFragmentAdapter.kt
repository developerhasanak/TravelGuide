package com.hasan.travelguide.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hasan.travelguide.databinding.FragmentHomeBinding
import com.hasan.travelguide.databinding.HomeDealsAllItemBinding
import com.hasan.travelguide.domain.model.remotemodel.Image
import com.hasan.travelguide.utils.GlideApp
import com.hasan.travelguide.utils.downloadFromUrl

class HomeFragmentAdapter (var imageList: List<Image>):RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentViewHolder>() {
    class HomeFragmentViewHolder(val binding: HomeDealsAllItemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentViewHolder {
        val binding = HomeDealsAllItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return HomeFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {
        try {
            holder.binding.homeDealsItemView.downloadFromUrl(imageList[position].url)
        } catch (e:Exception){
            e.message
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}