package com.hasan.travelguide.presentation.search.topdestinations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hasan.travelguide.databinding.TopDestinationsItemBinding
import com.hasan.travelguide.domain.model.remotemodel.AllTravelListItem
import com.hasan.travelguide.domain.model.remotemodel.Image
import com.hasan.travelguide.utils.downloadFromUrl

class TopDestinationsRecyclerViewAdapter(val infoList:List<AllTravelListItem>):RecyclerView.Adapter<TopDestinationsRecyclerViewAdapter.TopDestinationsRecyclerViewHolder>() {
    class TopDestinationsRecyclerViewHolder(val binding: TopDestinationsItemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopDestinationsRecyclerViewHolder {
        val binding = TopDestinationsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TopDestinationsRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopDestinationsRecyclerViewHolder, position: Int) {

        try {

              holder.binding.topDestinationsImageItem.downloadFromUrl(infoList[position].images?.get(position)?.url)
              holder.binding.topDestinotionsAlphaHeadTitle.text= infoList[position].city
              holder.binding.topDestinotionsAlphaBodytitle.text = infoList[position].country




        }catch (e:Exception){
            e.printStackTrace()
        }
        }

    override fun getItemCount(): Int {
       return infoList.size

    }
}