package com.joseoliva.losgoyapp.ui.ranking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.joseoliva.losgoyapp.data.models.RankingModel
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

    private var _rankingList = MutableStateFlow<List<RankingModel>>(emptyList())
    val rankingtList: StateFlow<List<RankingModel>> = _rankingList


    fun getRanking(){
        viewModelScope.launch {
            getRankingUseCase().collect{
                Log.d("preguntas", "la info es $it")
                _rankingList.value = it
            }
        }
    }
}