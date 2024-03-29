package com.joseoliva.losgoyapp.ui.ranking.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joseoliva.losgoyapp.data.models.RankingModel
import com.joseoliva.losgoyapp.databinding.ItemRankingBinding

class RankingViewHolder(view: View): RecyclerView.ViewHolder(view)  {

    private val binding = ItemRankingBinding.bind(view)

    fun bind(rankingModel: RankingModel){

        binding.tvnombreRanking.text = rankingModel.nombre.toString()
        binding.tvpuntosRanking.text = rankingModel.puntos.toString()

    }
}