package com.hasan.travelguide.presentation.home.deail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hasan.travelguide.databinding.FragmentHotelsBinding
import com.hasan.travelguide.presentation.home.HomeFragmentAdapter
import com.hasan.travelguide.presentation.home.HomeViewModel
import com.hasan.travelguide.utils.Status

class HotelsFragment : Fragment() {

    private var _binding: FragmentHotelsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: HomeFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHotelsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hotelsRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )

        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        observeAllDataList()
    }

    private fun observeAllDataList() {
        viewModel.hotelsList.observe(viewLifecycleOwner) { data ->
            data?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        val url = it.data?.flatMap { x -> x.images!! }
                        adapter = HomeFragmentAdapter(url!!)
                        binding.hotelsRecyclerView.adapter = adapter
                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {

                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}