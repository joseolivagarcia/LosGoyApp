package com.joseoliva.losgoyapp.ui.ranking

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseoliva.losgoyapp.databinding.ActivityRankingBinding
import com.joseoliva.losgoyapp.ui.main.MainActivity
import com.joseoliva.losgoyapp.ui.ranking.adapter.RankingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RankingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRankingBinding
    private val viewModel: RankingActivityViewModel by viewModels()
    private lateinit var rankingAdapter: RankingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getRanking()
        setUpUI()
        initListeners()
    }

    private fun setUpUI() {
        setUpRanking() //funcion para iniciar el recyclerview
        subscribeRanking()
    }

    private fun setUpRanking() {
        rankingAdapter = RankingAdapter(mutableListOf())
        binding.rvRanking.apply {
            adapter = rankingAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun subscribeRanking() {
        lifecycleScope.launch {
            viewModel.rankingtList.collect {
                rankingAdapter.updateList(it.toMutableList())
            }
        }
    }

    private fun initListeners(){
        binding.btnHome.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {

    }
}