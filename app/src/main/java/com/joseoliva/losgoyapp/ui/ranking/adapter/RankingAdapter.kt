package com.joseoliva.losgoyapp.ui.ranking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseoliva.losgoyapp.R
import com.joseoliva.losgoyapp.data.models.RankingModel
import java.util.Collections

class RankingAdapter(var listaRanking: MutableList<RankingModel>): RecyclerView.Adapter<RankingViewHolder>() {

    fun updateList(list: MutableList<RankingModel>){
        listaRanking.clear()
        listaRanking.addAll(list)
        Collections.reverse(listaRanking)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RankingViewHolder(layoutInflater.inflate(R.layout.item_ranking,parent,false))
    }

    override fun getItemCount(): Int = listaRanking.size

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.bind(listaRanking[position])
    }
}