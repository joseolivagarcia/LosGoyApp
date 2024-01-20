package com.joseoliva.losgoyapp.ui.main

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.joseoliva.losgoyapp.data.models.QuestionResponse
import com.joseoliva.losgoyapp.data.network.FirebaseService
import com.joseoliva.losgoyapp.domain.GetPreguntasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getPreguntasUseCase: GetPreguntasUseCase,
    private val reference: DatabaseReference,
    private val firebaseService: FirebaseService
): ViewModel() {
    fun sendContactToFirebase(){

        var preguntasGoya: MutableList<QuestionResponse> = mutableListOf()
        val pregunta1 = QuestionResponse(
            "¿En qué año se celebró la primera entrega de los premios Goya?",
            "1987",
            "1985",
            "1990",
            "1983",
            "1987")
        val pregunta2 = QuestionResponse(
            "¿Quién entregó la estatuilla número 0 de los premios Goya?",
            "Antonio Resines",
            "José Sacristán",
            "Juan Carlos I",
            "Mariano Barroso",
            "Juan Carlos I")
        val pregunta3 = QuestionResponse(
            "¿En qué año presidió la gala el entonces Príncipe Felipe?",
            "1987",
            "2000",
            "2004",
            "1998",
            "2000")
        val pregunta4 = QuestionResponse(
            "¿Quién fue el primer presidente del gobierno en acudir a una gala de los Goya?",
            "Jose Luis Rodriguez Zapatero",
            "Jose María Aznar",
            "Felipe González",
            "Mariano Rajoy",
            "Jose Luis Rodriguez Zapatero")
        val pregunta5 = QuestionResponse(
            "¿En qué ciudad se celebró por primera ves la gala en 2019?",
            "Barcelona",
            "Sevilla",
            "Oviedo",
            "Málaga",
            "Málaga")
        val pregunta6 = QuestionResponse(
            "¿Cuantos académicos tienen derecho a votar las películas que serán premiadas?",
            "1000",
            "250",
            "500",
            "300",
            "300")
        val pregunta7 = QuestionResponse(
            "¿Cuál es la película más premiada en la historia de los Goya?",
            "¡Ay Carmela!",
            "Blancanieves",
            "Días Contados",
            "Mar adentro",
            "Mar adentro")
        val pregunta8 = QuestionResponse(
            "¿Cuántos premios recibió la película Mar adentro convirtiéndose en la película más premiada de la histoira?",
            "12",
            "13",
            "14",
            "15",
            "14")
        val pregunta9 = QuestionResponse(
            "La película más nominada hasta la fecha (2024) es Días Contados, ¿cuántas nominaciones obtuvo?",
            "19",
            "20",
            "15",
            "12",
            "19")
        val pregunta10 = QuestionResponse(
            "¿Quién es el director con más candidaturas?",
            "Amenábar",
            "Almodóvar",
            "Mariano Barroso",
            "Fernando Trueba",
            "Almodóvar")
        val pregunta11 = QuestionResponse(
            "¿Quién es el actor con más nominaciones?",
            "Antonio Banderas",
            "Javier Bardem",
            "Antonio de la torre",
            "Eduard Fernandez",
            "Antonio de la torre")
        val pregunta12 = QuestionResponse(
            "¿Quién es el actor mas galardonado?",
            "Antonio Banderas",
            "Javier Bardem",
            "Antonio de la torre",
            "Eduard Fernandez",
            "Javier Bardem")

        preguntasGoya = mutableListOf(pregunta1,pregunta2,pregunta3, pregunta4,pregunta5,pregunta6,
            pregunta7,pregunta8,pregunta9,pregunta10,pregunta11,pregunta12)

        for (p in preguntasGoya){
            val newPregunta = reference.child("preguntas").push()
            newPregunta.setValue(p)
        }

    }
}