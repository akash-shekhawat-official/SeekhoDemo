package com.example.seekhodemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekhodemo.models.DetailPageResponse
import com.example.seekhodemo.repository.AnimeRepository
import com.example.seekhodemo.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val animeRepository: AnimeRepository) :
    ViewModel() {
    val animeDetailResponseLiveData: LiveData<NetworkResult<DetailPageResponse>>
        get() = animeRepository.animeDetailResponse

    fun getAmines(animId: Int) {
        viewModelScope.launch {
            animeRepository.getAnimeDetail(animId)
        }
    }
}