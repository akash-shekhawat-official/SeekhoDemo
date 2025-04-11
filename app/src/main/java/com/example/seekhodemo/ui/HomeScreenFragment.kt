package com.example.seekhodemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seekhodemo.adapter.HomeScreenAdapter
import com.example.seekhodemo.viewmodel.HomeViewModel
import com.example.seekhodemo.R
import com.example.seekhodemo.databinding.FragmentHomeScreenBinding
import com.example.seekhodemo.models.HomePageResponse
import com.example.seekhodemo.utils.Constants.ANIME_ID
import com.example.seekhodemo.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {
    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getApiResponse()

    }

    private fun getApiResponse() {
        homeViewModel.getAmines()
        homeViewModel.animeResponseLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Error -> {

                }

                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is NetworkResult.Success -> {

                    setViewData(it.data)
                }
            }
        }
    }

    private fun setViewData(data: HomePageResponse?) {
        val adapter = data?.let { it1 ->
            HomeScreenAdapter(it1.data, ::onCardClick)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    private fun onCardClick(animeId: Int) {
        val bundle = Bundle()
        bundle.putInt(ANIME_ID, animeId)
        findNavController().navigate(R.id.action_homeScreenFragment_to_detailScreenFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}