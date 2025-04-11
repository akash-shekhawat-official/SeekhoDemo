package com.example.seekhodemo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.seekhodemo.api.AnimeAPI
import com.example.seekhodemo.models.DetailPageResponse
import com.example.seekhodemo.models.HomePageResponse
import com.example.seekhodemo.utils.Constants.TAG
import com.example.seekhodemo.utils.NetworkResult
import javax.inject.Inject

class AnimeRepository @Inject constructor(private val animeAPI: AnimeAPI) {

    private val _animeResponseLiveData = MutableLiveData<NetworkResult<HomePageResponse>>()
    val animeResponseLiveData: LiveData<NetworkResult<HomePageResponse>>
        get() = _animeResponseLiveData
    private val _animeDetailResponse = MutableLiveData<NetworkResult<DetailPageResponse>>()
    val animeDetailResponse: LiveData<NetworkResult<DetailPageResponse>>
        get() = _animeDetailResponse

    suspend fun getAnimeData() {

        _animeResponseLiveData.postValue(NetworkResult.Loading())
        val response = animeAPI.getTopRatedAnime()
        if (response.isSuccessful && response.body() != null) {
            _animeResponseLiveData.postValue(NetworkResult.Success(response.body()!!))

            Log.d(TAG, response.body().toString())
        } else if (response.errorBody() != null) {
            _animeResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        } else {
            _animeResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun getAnimeDetail(animeId: Int) {

        val response = animeAPI.getAnimeDetail(animeId)
        _animeDetailResponse.postValue(NetworkResult.Loading())
        if (response.isSuccessful && response.body() != null) {
            _animeDetailResponse.postValue(NetworkResult.Success(response.body()!!))

            Log.d(TAG, response.body().toString())
        } else if (response.errorBody() != null) {
            _animeDetailResponse.postValue(NetworkResult.Error("Something went wrong"))
        } else {
            _animeDetailResponse.postValue(NetworkResult.Error("Something went wrong"))
        }
        Log.d(TAG, response.body().toString())
    }
}