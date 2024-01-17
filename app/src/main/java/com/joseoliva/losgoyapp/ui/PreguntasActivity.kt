package com.joseoliva.losgoyapp.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.joseoliva.losgoyapp.data.response.QuestionResponse
import com.joseoliva.losgoyapp.databinding.ActivityPreguntasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreguntasActivity: AppCompatActivity(){

    private lateinit var binding: ActivityPreguntasBinding
    private var listaPreguntas: MutableList<QuestionResponse> = mutableListOf()

    var numAle: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreguntasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listaPreguntas = this.intent.getParcelableArrayListExtra("preguntas")!!
        setPlay()
    }

    private fun setPlay() {
        empezarJuego()
        contadorCircular()
    }

    private fun empezarJuego() {

        Log.d("preguntas", "la lista tiene ${listaPreguntas.size}")
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

    private fun initListeners(num: Int) {
        binding.tvrespuestaA.setOnClickListener {

            if (binding.tvrespuestaA.text.equals(listaPreguntas[num].respuestaCorrecta.toString())) {
                Toast.makeText(this, "Has acertado", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this, "Has acertado", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this, "Has acertado", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this, "Has acertado", Toast.LENGTH_SHORT).show()
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
            setProgressWithAnimation(1f, 60000)

            progressMax = 1f
            progressBarColor = Color.BLUE
            backgroundProgressBarColor = Color.GRAY

            binding.progressCircular.onProgressChangeListener = { progress ->
                //aqui definimos lo que queremos cuando termine el progreso o en el punto que queramos del progreso
                if (progress == 0.5f){
                    Toast.makeText(context,"Mitad", Toast.LENGTH_SHORT).show()
                }else if (progress == 1f){
                    Toast.makeText(context,"Fin", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

}