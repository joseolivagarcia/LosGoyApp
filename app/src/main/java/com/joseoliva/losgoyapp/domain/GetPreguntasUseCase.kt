package com.joseoliva.losgoyapp.domain

import com.joseoliva.losgoyapp.data.network.FirebaseService
import javax.inject.Inject

class GetPreguntasUseCase @Inject constructor(private val firebaseService: FirebaseService) {

    operator fun invoke() = firebaseService.getPreguntas()
}