package com.hasan.travelguide.presentation.home

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.hasan.travelguide.R
import com.hasan.travelguide.databinding.FragmentHomeBinding
import com.hasan.travelguide.domain.model.remotemodel.AllTravelListItem
import com.hasan.travelguide.domain.model.remotemodel.Image

import com.hasan.travelguide.utils.Status
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeFragmentAdapter
    private val viewModel by viewModels<HomeViewModel>()
    private var listAllData = ArrayList<Image>()
    private var flightList = ArrayList<Image>()
    private var hotelList = ArrayList<Image>()
    private var transportationList = ArrayList<Image>()
    private var state:Parcelable? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)




        observeAllDataList()
        observeFlightsDataList()
        observeHotelsDataList()
        observeTransportationsDataList()
        tabAdapterconnection()
    }

    /**
     *This method connects homeViewpager2 with tab adapter and assigns text for selected tab items
     *
     */
    private fun tabAdapterconnection() {


        binding.homeTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null) {
                    when(tab.position){
                        0 -> {
                            tab.text = "All"
                            adapter = HomeFragmentAdapter(listAllData)
                            binding.homeRecyclerView.adapter = adapter

                        }

                        1 -> {
                            tab.text = "Flights"
                            adapter = HomeFragmentAdapter(flightList)
                            binding.homeRecyclerView.adapter = adapter

                        }

                        2 -> {
                            tab.text = "Hotels"
                            adapter = HomeFragmentAdapter(hotelList)
                            binding.homeRecyclerView.adapter = adapter

                        }

                        3->{
                            tab.text = "Transportation"
                            adapter = HomeFragmentAdapter(transportationList)
                            binding.homeRecyclerView.adapter = adapter

                        }

                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {


            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })



    }

    private fun observeAllDataList(){
         viewModel.allList.observe(viewLifecycleOwner){ data ->
             data?.let {
                 when(it.status){
                     Status.SUCCESS -> {
                      listAllData = it.data?.flatMap { x -> x.images!! } as ArrayList

                         adapter = HomeFragmentAdapter(listAllData)
                         binding.homeRecyclerView.adapter = adapter
                     }
                     Status.ERROR -> {

                     }
                     Status.LOADING -> {

                     }
                 }
             }
         }


    }


    private fun observeFlightsDataList(){
        viewModel.flightsList.observe(viewLifecycleOwner){ data ->
            data?.let {
                when(it.status){
                    Status.SUCCESS -> {
                        flightList = it.data?.flatMap { x -> x.images!! } as ArrayList

                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        }


    }

    private fun observeHotelsDataList(){
        viewModel.allList.observe(viewLifecycleOwner){ data ->
            data?.let {
                when(it.status){
                    Status.SUCCESS -> {
                        hotelList = it.data?.flatMap { x -> x.images!! } as ArrayList

                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        }


    }

    private fun observeTransportationsDataList(){
        viewModel.transpotationList.observe(viewLifecycleOwner){ data ->
            data?.let {
                when(it.status){
                    Status.SUCCESS -> {
                        transportationList = it.data?.flatMap { x -> x.images!! } as ArrayList

                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        state =binding.homeRecyclerView.layoutManager?.onSaveInstanceState()
    }

    override fun onResume() {
        super.onResume()
          binding.homeRecyclerView.layoutManager?.onRestoreInstanceState(state)

    }

}