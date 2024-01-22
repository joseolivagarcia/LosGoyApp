package com.joseoliva.losgoyapp.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.joseoliva.losgoyapp.data.models.QuestionResponse
import com.joseoliva.losgoyapp.data.models.RankingModel
import com.joseoliva.losgoyapp.data.network.FirebaseService
import com.joseoliva.losgoyapp.domain.GetPreguntasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
    private val reference: DatabaseReference,
    private val getPreguntasUseCase: GetPreguntasUseCase,
    private val firebaseService: FirebaseService
): ViewModel() {

    private var _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    var listado: MutableList<QuestionResponse> = mutableListOf()
    var listadoRanking: MutableList<RankingModel> = mutableListOf()

    fun dataFromFirebase(): Pair<MutableList<QuestionResponse>,MutableList<RankingModel>> {
        viewModelScope.launch {
            _isLoading.value = true
            val result = withContext(Dispatchers.IO) {
                firebaseService.getAllPreguntas()
            }
            val resultRanking = withContext(Dispatchers.IO){
                firebaseService.getFirstRanking()
            }
            if (result != null && resultRanking != null) {
                listado = result.toMutableList()
                listadoRanking = resultRanking.toMutableList()
                Log.i("preguntas", "tengo lista de ${listado.size}")
                Log.i("preguntas", "tengo ranking de ${listadoRanking.size}")
            } else {
                Log.i("preguntas", "error de conexion")
            }

            _isLoading.value = false

        }
        return Pair(listado,listadoRanking)
    }
}


