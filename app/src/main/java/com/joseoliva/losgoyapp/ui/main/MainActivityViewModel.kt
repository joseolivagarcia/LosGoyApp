package com.joseoliva.losgoyapp.ui.main

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.joseoliva.losgoyapp.data.models.QuestionResponse
import com.joseoliva.losgoyapp.data.models.RankingModel
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

    fun sendRankingToFirebase(){
        var rankinglista: MutableList<RankingModel> = mutableListOf()
        val ranking1 = RankingModel("","jose",175)
        val ranking2 = RankingModel("","esther",25)
        rankinglista = mutableListOf(ranking1,ranking2)
        for (r in rankinglista){
            val newRanking = reference.child("ranking").push()
            newRanking.setValue(r)
        }
    }
    fun sendPreguntasToFirebase(){

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
            "La película más nominada hasta la fecha (2024) es El buen patrón, ¿cuántas nominaciones obtuvo?",
            "19",
            "20",
            "15",
            "12",
            "20")
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
        val pregunta13 = QuestionResponse(
            "¿Por qué película ganó Fernando León de Aranoa su goya a mejor director novel?",
            "Barrio",
            "Los lunes al sol",
            "Familia",
            "Un día perfecto",
            "Familia")
        val pregunta14 = QuestionResponse(
            "¿En qué año se introdujo la categoría de mejor película de animación?",
            "1990",
            "1991",
            "1992",
            "1993",
            "1990")
        val pregunta15 = QuestionResponse(
            "¿En qué año se introdujo la categoría de mejor película documental?",
            "2000",
            "2001",
            "2002",
            "2003",
            "2002")
        val pregunta16 = QuestionResponse(
            "¿En qué año se introdujo la categoría de mejor guión adaptado?",
            "1986",
            "1987",
            "1988",
            "1989",
            "1989")
        val pregunta17 = QuestionResponse(
            "¿En qué año se introdujo la categoría de mejor cortometraje documental?",
            "1993",
            "1994",
            "1995",
            "1996",
            "1993")
        val pregunta18 = QuestionResponse(
            "¿Quién fue la primera mujer en hacerse con la estatuilla a mejor dirección?",
            "Isabel Coixet",
            "Iciar Bollaín",
            "Pilar Miró",
            "Anabel Castro",
            "Pilar Miró")
        val pregunta19 = QuestionResponse(
            "¿Quién fue la primera mujer guionista en hacerse con la estatuilla a mejor guión?",
            "Isabel Coixet",
            "Yolanda García Serrano",
            "Pilar Miró",
            "Iciar Bollaín",
            "Yolanda García Serrano")
        val pregunta20 = QuestionResponse(
            "¿Cuántas veces ha ganado Enrique Gato el goya a mejor película de animación?",
            "Ninguna",
            "1",
            "2",
            "3",
            "2")
        val pregunta21 = QuestionResponse(
            "¿Qué escultor diseñó la primera estatuilla?",
            "José Luis Fernandez",
            "Pedro Alonso Martínez",
            "Francisco Vázquez",
            "Miguel Ortiz Berrocal",
            "Miguel Ortiz Berrocal")
        val pregunta22 = QuestionResponse(
            "¿Qué escultor diseñó la estatuilla a partir de 1990?",
            "José Luis Fernandez",
            "Pedro Alonso Martínez",
            "Francisco Vázquez",
            "Miguel Ortiz Berrocal",
            "José Luis Fernandez")
        val pregunta23 = QuestionResponse(
            "¿Cuánto pesaba la primera estatuilla que se diseñó?",
            "20kg",
            "15kg",
            "18kgz",
            "12kg",
            "15kg")
        val pregunta24 = QuestionResponse(
            "¿Dónde se celebró la primera gala de Los Goya?",
            "Teatro Lope de Vega",
            "Circo Price",
            "Palacio de Congresos",
            "Teatro Real",
            "Teatro Lope de Vega")
        val pregunta25 = QuestionResponse(
            "¿Dónde se celebró la primera gala de Los Goya?",
            "Teatro Lope de Vega",
            "Circo Price",
            "Palacio de Congresos",
            "Teatro Real",
            "Teatro Lope de Vega")
        val pregunta26 = QuestionResponse(
            "¿Dónde se celebró la primera gala de Los Goya que salió de Madrid?",
            "Sevilla",
            "Málaga",
            "Valencia",
            "Barcelona",
            "Barcelona")
        val pregunta27 = QuestionResponse(
            "¿Dónde se celebró la última gala de los Goya (2024)?",
            "Salamanca",
            "Valladolid",
            "Valencia",
            "Burgos",
            "Valladolid")
        val pregunta28 = QuestionResponse(
            "¿Quién es la persona con más estatuillas?",
            "Javier Bardem",
            "Alberto Iglesias",
            "Alejandro Amenábar",
            "Pedro Almodóvar",
            "Alberto Iglesias")
        val pregunta29 = QuestionResponse(
            "¿Qué actriz cuenta en su haber con más nominaciones?",
            "Veronica Forqué",
            "Carmen Maura",
            "Penélope Cruz",
            "Carmen Machi",
            "Penélope Cruz")
        val pregunta30 = QuestionResponse(
            "¿Por qué no recogió sus premios Fernando Fernán Gómez en 1987?",
            "Se quedó durmiendo en casa",
            "Estaba rodando fuera de España",
            "Se encontraba enfermo",
            "Renegaba de la academia",
            "Se quedó durmiendo en casa")


        preguntasGoya = mutableListOf(pregunta1,pregunta2,pregunta3, pregunta4,pregunta5,pregunta6,
            pregunta7,pregunta8,pregunta9,pregunta10,pregunta11,pregunta12,pregunta13,pregunta14,pregunta15,
            pregunta16,pregunta17,pregunta18,pregunta19,pregunta20,pregunta21,pregunta22,pregunta23,pregunta24,
            pregunta25,pregunta26,pregunta27,pregunta28,pregunta29,pregunta30)

        for (p in preguntasGoya){
            val newPregunta = reference.child("preguntas").push()
            newPregunta.setValue(p)
        }

    }
}