package com.joseoliva.losgoyapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.joseoliva.losgoyapp.data.response.QuestionResponse
import com.joseoliva.losgoyapp.databinding.ActivityPreguntasBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PreguntasActivity: AppCompatActivity() {

    private lateinit var binding: ActivityPreguntasBinding
    private val viewmodel: PreguntasActivityViewModel by viewModels<PreguntasActivityViewModel>()
    var listaPreguntas: MutableList<QuestionResponse> = emptyList<QuestionResponse>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreguntasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel.getPreguntas()
        setupUI()
    }

    private fun setupUI() {
        subscribePreguntas()
    }

    private fun subscribePreguntas() {
        lifecycleScope.launch{
            viewmodel.preguntasList.collect{
                listaPreguntas.addAll(it.toMutableList())
                Log.d("preguntas", "la lista tiene ${listaPreguntas.size}")
                emprezarJuego()
            }
        }
    }

    private fun emprezarJuego() {

    }

}