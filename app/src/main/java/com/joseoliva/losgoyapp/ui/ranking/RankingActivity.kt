package com.joseoliva.losgoyapp.ui.ranking

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseoliva.losgoyapp.databinding.ActivityRankingBinding
import com.joseoliva.losgoyapp.ui.ranking.adapter.RankingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RankingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRankingBinding
    private val viewModel: RankingActivityViewModel by viewModels()
    private lateinit var rankingAdapter: RankingAdapter
    //private var puntuacion: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*puntuacion = this.intent.getIntExtra("puntuacion", 0)
        Log.i("preguntas", "recibo: $puntuacion puntos")*/

        viewModel.getRanking()
        setUpUI()
        //viewModel.sendRankingToFirebase()
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

        /*lifecycleScope.launch {
            delay(1000)
            comprobarPuntuacion()
        }

    }

    private fun comprobarPuntuacion() {
        if(viewModel.rankingtList.value.get(0).puntos?.toInt()!! < puntuacion){
            Toast.makeText(this,"Entras en el ranking!!",Toast.LENGTH_SHORT).show()
            Log.i("preguntas","Entras en el ranking con: $puntuacion")
        }
    }*/

    }
}