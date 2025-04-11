package com.example.seekhodemo.api

import com.example.seekhodemo.models.DetailPageResponse
import com.example.seekhodemo.models.HomePageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeAPI {

    @GET("top/anime")
    suspend fun getTopRatedAnime(): Response<HomePageResponse>

    @GET("anime/{animeId}")
    suspend fun getAnimeDetail(@Path("animeId") animeId: Int): Response<DetailPageResponse>


}