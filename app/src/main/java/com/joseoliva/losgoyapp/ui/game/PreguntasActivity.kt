package com.joseoliva.losgoyapp.ui.game

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.joseoliva.losgoyapp.data.models.QuestionResponse
import com.joseoliva.losgoyapp.data.models.rankingModel
import com.joseoliva.losgoyapp.databinding.ActivityPreguntasBinding
import com.joseoliva.losgoyapp.ui.ranking.RankingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PreguntasActivity: AppCompatActivity(){

    private lateinit var binding: ActivityPreguntasBinding
    private var listaPreguntas: MutableList<QuestionResponse> = mutableListOf()
    private var listaRankig: MutableList<rankingModel> = mutableListOf()

    var numAle: Int = 0
    private var score = 0
    private var totalScore = 0
    private var menorPuntuacion = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreguntasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listaPreguntas = this.intent.getParcelableArrayListExtra("preguntas")!!
        listaRankig = this.intent.getParcelableArrayListExtra("ranking")!!

        menorPuntuacion = listaRankig[0].puntos!!
        for (r in listaRankig){
            Log.i("preguntas","puntuacion vale ${r.puntos}")
            if(menorPuntuacion < r.puntos!!){
                menorPuntuacion = menorPuntuacion
            }else{
                menorPuntuacion = r.puntos!!
            }
        }
        Log.i("preguntas","menor puntuacion vale $menorPuntuacion")
        setPlay()
    }

    private fun setPlay() {
        empezarJuego()
        contadorCircular()
    }

    private fun empezarJuego() {

        Log.d("preguntas", "la lista tiene ${listaPreguntas.size}")
        Log.d("preguntas", "el ranking tiene ${listaRankig.size}")
        if(listaPreguntas.size >= 1){
            numAle = (0..listaPreguntas.size -1).random()

            binding.tvpregunta.text =   listaPreguntas[numAle].pregunta.toString()
            binding.tvrespuestaA.text = listaPreguntas[numAle].respuestaA.toString()
            binding.tvrespuestaB.text = listaPreguntas[numAle].respuestaB.toString()
            binding.tvrespuestaC.text = listaPreguntas[numAle].respuestaC.toString()
            binding.tvrespuestaD.text = listaPreguntas[numAle].respuestaD.toString()
        }else{
            Toast.makeText(this, "Se acabaron las preguntas", Toast.LENGTH_SHORT).show()
            binding.tvrespuestaA.isEnabled = false
            binding.tvrespuestaB.isEnabled = false
            binding.tvrespuestaC.isEnabled = false
            binding.tvrespuestaD.isEnabled = false
        }

        initListeners(numAle)
    }

    private fun actualizarMarcador(){
        totalScore = score + 25
        score = totalScore
        binding.tvscore.text = totalScore.toString()
    }

    private suspend fun finalizarJuego(){
        comprobarPuntuacion()
        delay(4000)
        val intent = Intent(this, RankingActivity::class.java)
        startActivity(intent)
    }

    private fun comprobarPuntuacion() {
        if (menorPuntuacion < totalScore){
            Log.i("preguntas", "la puntuacion mas baja es: $menorPuntuacion")
            Log.i("preguntas", "Mi puntuacion es: $totalScore")
            Toast.makeText(this,"Enhorabuena! entras en el top 5!!!",Toast.LENGTH_SHORT).show()
        }else{
            Log.i("preguntas", "la puntuacion mas baja es: $menorPuntuacion")
            Log.i("preguntas", "Mi puntuacion es: $totalScore")
            Toast.makeText(this,"NO entras en el top 5!!!",Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListeners(num: Int) {

        val colorAcierto = ColorStateList.valueOf(Color.GREEN)
        val colorFallo = ColorStateList.valueOf(Color.RED)

        binding.tvrespuestaA.setOnClickListener {
            if (binding.tvrespuestaA.text.equals(listaPreguntas[num].respuestaCorrecta.toString())) {
                actualizarMarcador()
                listaPreguntas.removeAt(num)
                empezarJuego()
            } else {
                Toast.makeText(this, "Has fallado", Toast.LENGTH_SHORT).show()
                listaPreguntas.removeAt(num)
                empezarJuego()
            }
        }
        binding.tvrespuestaB.setOnClickListener {
            if (binding.tvrespuestaB.text.equals(listaPreguntas[num].respuestaCorrecta.toString())) {
                actualizarMarcador()
                listaPreguntas.removeAt(num)
                empezarJuego()
            } else {
                Toast.makeText(this, "Has fallado", Toast.LENGTH_SHORT).show()
                listaPreguntas.removeAt(num)
                empezarJuego()
            }
        }
        binding.tvrespuestaC.setOnClickListener {
            if (binding.tvrespuestaC.text.equals(listaPreguntas[num].respuestaCorrecta.toString())) {
                actualizarMarcador()
                listaPreguntas.removeAt(num)
                empezarJuego()
            } else {
                Toast.makeText(this, "Has fallado", Toast.LENGTH_SHORT).show()
                listaPreguntas.removeAt(num)
                empezarJuego()
            }
        }
        binding.tvrespuestaD.setOnClickListener {
            if (binding.tvrespuestaD.text.equals(listaPreguntas[num].respuestaCorrecta.toString())) {
                actualizarMarcador()
                listaPreguntas.removeAt(num)
                empezarJuego()
            } else {
                Toast.makeText(this, "Has fallado", Toast.LENGTH_SHORT).show()
                listaPreguntas.removeAt(num)
                empezarJuego()
            }
        }
    }

    private fun contadorCircular(){
        binding.progressCircular.apply {
            setProgressWithAnimation(1f, 10000)

            progressMax = 1f
            progressBarColor = Color.BLUE
            backgroundProgressBarColor = Color.GRAY

            binding.progressCircular.onProgressChangeListener = { progress ->
                //aqui definimos lo que queremos cuando termine el progreso o en el punto que queramos del progreso
                if (progress == 1f){
                    binding.tvrespuestaA.isEnabled = false
                    binding.tvrespuestaB.isEnabled = false
                    binding.tvrespuestaC.isEnabled = false
                    binding.tvrespuestaD.isEnabled = false
                    lifecycleScope.launch {
                        finalizarJuego()
                    }

                    }
                }

            }
        }


}