package com.joseoliva.losgoyapp.data.local

import com.joseoliva.losgoyapp.data.models.QuestionResponse

class DatosPreguntas {

    val pregunta1 = QuestionResponse(
        "¿Quién ganó el balón de oro de fútbol en 2023?",
        "Messi",
        "Cristiano Ronaldo",
        "Benzema",
        "Griezman",
        "Messi"
    )
    val pregunta2 = QuestionResponse(
        "¿Qué jugador es el máximo goleador mundial en 2023?",
        "Messi",
        "Benzema",
        "Griezman",
        "Cristiano Ronaldo",
        "Cristiano Ronaldo"
    )
    val pregunta3 = QuestionResponse(
        "Campeón de invierno de la liga santander 2023",
        "Girona",
        "FC Barcelona",
        "Atlético de Madrid",
        "Real Madrid",
        "Real Madrid"
    )
    val pregunta4 = QuestionResponse(
        "¿En qué equipo español jugó Hugo Maradona?",
        "Rayo Vallecano",
        "Las Palmas",
        "Cádiz CF",
        "Real Betis",
        "Rayo Vallecano"
    )
    val pregunta5 = QuestionResponse(
        "¿En qué equipo debutó Óscar Oliva?",
        "Nuevo Boadilla",
        "Parla FC",
        "EFMO Boadilla",
        "Betis Balompié",
        "EFMO Boadilla"
    )
    val pregunta6 = QuestionResponse(
        "¿Quién ha ganado el premio The Best 2023?",
        "Messi",
        "Haland",
        "Ronaldo",
        "Mbapé",
        "Messi"
    )

    var lista: MutableList<QuestionResponse> = mutableListOf<QuestionResponse>(pregunta1,pregunta2,pregunta3,
        pregunta4,pregunta5,pregunta6)

}