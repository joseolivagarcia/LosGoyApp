package com.joseoliva.losgoyapp.ui.ranking

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseoliva.losgoyapp.data.models.rankingModel
import com.joseoliva.losgoyapp.databinding.ActivityRankingBinding
import com.joseoliva.losgoyapp.ui.ranking.adapter.RankingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RankingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRankingBinding
    private val viewModel: RankingActivityViewModel by viewModels()
    private lateinit var rankingAdapter: RankingAdapter
    private var lista: MutableList<rankingModel> = mutableListOf<rankingModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lista = mutableListOf(rankingModel("pepe", "240"),rankingModel("luis","222"))

        viewModel.getRanking()
        setUpUI()
        //viewModel.sendRankingToFirebase()
    }

    private fun setUpUI() {
        setUpRanking() //funcion para iniciar el recyclerview
        subscribeContacts()
    }

    private fun setUpRanking() {
        rankingAdapter = RankingAdapter(mutableListOf())
        binding.rvRanking.apply {
            adapter = rankingAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun subscribeContacts(){
        lifecycleScope.launch {
            viewModel.rankingtList.collect{
                rankingAdapter.updateList(it.toMutableList())
            }
        }
    }

}