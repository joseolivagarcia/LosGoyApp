package com.joseoliva.losgoyapp.ui

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.joseoliva.losgoyapp.data.network.FirebaseService
import com.joseoliva.losgoyapp.data.response.QuestionResponse
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

        val contact = QuestionResponse(
            "Sistema operativo que mas te gusta usar?",
            "Android",
            "IOs",
            "Windows",
            "Linux",
            "Android")
        val newContact = reference.child("preguntas").push() //hemos creado una constante para no cometer errores.
        newContact.setValue(contact)
    }
}