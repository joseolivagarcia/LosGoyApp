package com.joseoliva.losgoyapp.domain

import com.joseoliva.losgoyapp.data.network.FirebaseService
import javax.inject.Inject

class GetRankingUseCase @Inject constructor(private val firebaseService: FirebaseService) {
    operator fun invoke() = firebaseService.getRanking() //aqui recibo todos los contactos
}