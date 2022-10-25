package com.hasan.travelguide.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.hasan.travelguide.R
import com.hasan.travelguide.databinding.FragmentSearchBinding
import com.hasan.travelguide.presentation.search.nearbyattractions.NearbyAttractionsRecyclerAdapter
import com.hasan.travelguide.presentation.search.topdestinations.TopDestinationsRecyclerViewAdapter
import com.hasan.travelguide.utils.Status

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var topItemADApter:TopDestinationsRecyclerViewAdapter
    private lateinit var nearbyItemAdapter:NearbyAttractionsRecyclerAdapter
    private  lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //In the recycler view, the horizontal placement of the items is defined.
        binding.topDestinationsRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        viewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]

        binding.nearybyAttractionsRecyclerView.layoutManager = LinearLayoutManager(context)

        observeTopdestinations()
    }

    private fun observeTopdestinations(){
        viewModel.topDectinations.observe(viewLifecycleOwner) { data ->
            data?.let {
                when (data.status) {
                    Status.SUCCESS -> {
                        val list = it.data
                        topItemADApter = TopDestinationsRecyclerViewAdapter(list!!)
                        binding.topDestinationsRecyclerView.adapter = topItemADApter
                    }

                    Status.LOADING -> {

                    }

                    Status.ERROR -> {

                    }
                }

            }
        }
    }
}