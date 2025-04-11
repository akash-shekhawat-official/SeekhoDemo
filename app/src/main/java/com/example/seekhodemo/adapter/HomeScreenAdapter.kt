package com.example.seekhodemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.seekhodemo.databinding.HomeDetailCardBinding
import com.example.seekhodemo.models.Data

class HomeScreenAdapter(private val animeList: List<Data>, private val onCardClick: (Int) -> Unit) :
    RecyclerView.Adapter<HomeScreenAdapter.HomeViewHolder>() {
    inner class HomeViewHolder(val binding: HomeDetailCardBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            HomeDetailCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val anime = animeList[position]
        with(holder.binding) {
            textTitle.text = anime.title
            textEpisodes.text = "Episodes: ${anime.episodes}"
            textRating.text = "Rating: ${anime.rating}"
            Glide.with(root.context)
                .load(anime.images.jpg.image_url)
                .fitCenter()
                .into(imagePoster)
            root.setOnClickListener {
                onCardClick(anime.mal_id)
            }
        }
    }

    override fun getItemCount() = animeList.size
}
