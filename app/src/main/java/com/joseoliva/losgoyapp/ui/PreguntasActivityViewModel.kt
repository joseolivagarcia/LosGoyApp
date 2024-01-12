package com.joseoliva.losgoyapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.joseoliva.losgoyapp.data.response.QuestionResponse
import com.joseoliva.losgoyapp.domain.GetPreguntasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreguntasActivityViewModel @Inject constructor(
    private val reference: DatabaseReference,
    private val getPreguntasUseCase: GetPreguntasUseCase): ViewModel() {

    private var _preguntasList = MutableStateFlow<List<QuestionResponse>>(emptyList())
    val preguntasList: StateFlow<List<QuestionResponse>> = _preguntasList

    fun getPreguntas(){
        viewModelScope.launch {
            getPreguntasUseCase().collect() {
                _preguntasList.value = it
            }
        }
    }
    fun sendContactToFirebase(){

        val contact = QuestionResponse("Donde vives?","Madrid","Cadiz","Barcelona","Burgos","Burgos")
        val newContact = reference.child("preguntas").push() //hemos creado una constante para no cometer errores.
        newContact.setValue(contact)
    }
}
