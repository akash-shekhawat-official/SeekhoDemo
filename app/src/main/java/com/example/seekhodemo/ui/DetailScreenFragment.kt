package com.example.seekhodemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.seekhodemo.viewmodel.DetailViewModel
import com.example.seekhodemo.databinding.FragmentDetailScreenBinding
import com.example.seekhodemo.models.DataX
import com.example.seekhodemo.utils.Constants.ANIME_ID
import com.example.seekhodemo.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailScreenFragment : Fragment() {

    private var _binding: FragmentDetailScreenBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel by viewModels<DetailViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()


    }


    private fun setInitialData() {
        val getAnimId = arguments?.getInt(ANIME_ID)
        if (getAnimId != null) {
            detailViewModel.getAmines(getAnimId)
        }
        detailViewModel.animeDetailResponseLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Error -> {

                }

                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is NetworkResult.Success -> {
                    setViewData(it.data?.data)
                }
            }
        }
    }

    private fun setViewData(data: DataX?) {
        data?.apply {

            binding.detailTitle.text = title_english
            binding.detailSynopsis.text = synopsis
            binding.detailEpisodes.text = "Episodes: ${episodes}"
            binding.detailRating.text = "Rating: ${score}"
            binding.detailGenres.text =
                "Genres: " + genres.joinToString(", ") { it.name }


            binding.posterImage.visibility = View.VISIBLE
            Glide.with(requireContext()).load(images.jpg.large_image_url).fitCenter()
                .into(binding.posterImage)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}