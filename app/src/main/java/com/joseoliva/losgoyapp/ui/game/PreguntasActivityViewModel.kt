package com.joseoliva.losgoyapp.ui.game

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.joseoliva.losgoyapp.data.models.RankingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreguntasActivityViewModel @Inject constructor(
    private val reference: DatabaseReference
): ViewModel() {

    fun writeOnRanking(nombre: String, puntos: Int){
        val newObject = RankingModel("",nombre,puntos)
        val newRanking = reference.child("ranking").push()
        newRanking.setValue(newObject)

    }

}
