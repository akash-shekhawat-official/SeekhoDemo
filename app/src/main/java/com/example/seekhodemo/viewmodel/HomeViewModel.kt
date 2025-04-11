package com.example.seekhodemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekhodemo.models.HomePageResponse
import com.example.seekhodemo.repository.AnimeRepository
import com.example.seekhodemo.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val animeRepository: AnimeRepository) :
    ViewModel() {

    val animeResponseLiveData: LiveData<NetworkResult<HomePageResponse>>
        get() = animeRepository.animeResponseLiveData

    fun getAmines() {
        viewModelScope.launch {
            animeRepository.getAnimeData()
        }
    }
}