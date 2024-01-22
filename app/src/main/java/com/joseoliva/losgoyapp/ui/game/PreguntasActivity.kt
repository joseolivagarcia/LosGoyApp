package com.joseoliva.losgoyapp.ui.game

import android.app.Dialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.joseoliva.losgoyapp.data.models.QuestionResponse
import com.joseoliva.losgoyapp.data.models.RankingModel
import com.joseoliva.losgoyapp.databinding.ActivityPreguntasBinding
import com.joseoliva.losgoyapp.databinding.DialogItemRankingBinding
import com.joseoliva.losgoyapp.ui.main.MainActivity
import com.joseoliva.losgoyapp.ui.ranking.RankingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PreguntasActivity: AppCompatActivity(){

    private lateinit var binding: ActivityPreguntasBinding
    private val viewmodel: PreguntasActivityViewModel by viewModels()
    private var listaPreguntas: MutableList<QuestionResponse> = mutableListOf()
    private var listaRankig: MutableList<RankingModel> = mutableListOf()

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
            if(menorPuntuacion < r.puntos!!){
                menorPuntuacion = menorPuntuacion
            }else{
                menorPuntuacion = r.puntos!!
            }
        }
        setPlay()
    }

    private fun setPlay() {
        empezarJuego()
        contadorCircular()
    }

    private fun empezarJuego() {
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

    private fun finalizarJuego(){
        comprobarPuntuacion()

    }

    private fun comprobarPuntuacion() {
        if (menorPuntuacion < totalScore){
            showDialog()
        }else{
            Toast.makeText(this,"NO entras en el top 5!!!",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDialog() {
            val binding = DialogItemRankingBinding.inflate(layoutInflater) //podemos crear aqui mismo un binding
            val dialog = Dialog(this)
            dialog.setContentView(binding.root) //esto es igual que en las activities

            dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) //esto es para que la ventana del dialogo se adapte a las medidas.

            binding.btnEnviar.setOnClickListener {

                val nombre = binding.etNombre.text.toString()
                if(nombre.isEmpty()){
                    Toast.makeText(this,"Debes escribir un nombre", Toast.LENGTH_SHORT).show()
                }else{
                    viewmodel.writeOnRanking(nombre,totalScore)
                    val intent = Intent(this, RankingActivity::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }
            }
            dialog.show()
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

    override fun onBackPressed() {

    }
}