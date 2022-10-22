package com.hasan.travelguide.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hasan.travelguide.R
import com.hasan.travelguide.databinding.FragmentHomeBinding
import com.hasan.travelguide.presentation.home.deail.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

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

        tabAdapterconnection()
    }

    /**
     *This method connects homeViewpager2 with tab adapter and assigns text for selected tab items
     *
     */
    private fun tabAdapterconnection() {

        val tabList = arrayOf(
            getString(R.string.all),
            getString(R.string.filgts),
            getString(R.string.hotels),
            getString(R.string.transportations)
        )

        binding.apply {
            homeViewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
            homeViewPager.isUserInputEnabled = false

            TabLayoutMediator(homeTabLayout, homeViewPager) { tab, position ->
                tab.text = tabList[position]
            }.attach()
        }
    }

}