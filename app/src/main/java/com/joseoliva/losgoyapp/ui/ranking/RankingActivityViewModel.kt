package com.joseoliva.losgoyapp.ui.ranking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.joseoliva.losgoyapp.data.models.rankingModel
import com.joseoliva.losgoyapp.data.network.FirebaseService
import com.joseoliva.losgoyapp.domain.GetRankingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingActivityViewModel @Inject constructor(
    private val firebaseService: FirebaseService,
    private val reference: DatabaseReference,
    private val getRankingUseCase: GetRankingUseCase
): ViewModel() {

    private var _rankingList = MutableStateFlow<List<rankingModel>>(emptyList())
    val rankingtList: StateFlow<List<rankingModel>> = _rankingList


    fun getRanking(){
        viewModelScope.launch {
            getRankingUseCase().collect{
                Log.d("preguntas", "la info es $it")
                Log.d("preguntas", "la menor puntuacion es  ${it.get(0).puntos}")
                _rankingList.value = it
            }
        }
    }

    //para pruebas
    fun sendRankingToFirebase(){
        var rankinglista: MutableList<rankingModel> = mutableListOf()
        val ranking1 = rankingModel("jose","275")
        val ranking2 = rankingModel("esther","150")
        val ranking3 = rankingModel("lukas","225")
        val ranking4 = rankingModel("oscar","350")
        val ranking5 = rankingModel("koda","150")
        val ranking6 = rankingModel("Pepe","255")
        rankinglista = mutableListOf(ranking1,ranking2,ranking3,ranking4,ranking5,ranking6)
        for (r in rankinglista){
            val newRanking = reference.child("ranking").push()
            newRanking.setValue(r)
        }
    }
}