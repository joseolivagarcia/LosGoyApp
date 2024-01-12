package com.joseoliva.losgoyapp.data.network

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import com.joseoliva.losgoyapp.data.response.QuestionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseService @Inject constructor( private val reference: DatabaseReference) {

    companion object{
        private const val PATH = "preguntas"
    }

    fun getPreguntas(): Flow<List<QuestionResponse>> {
        return reference.child(PATH).snapshots.map { dataSnapshot: DataSnapshot ->
            dataSnapshot.children.mapNotNull {
                it.getValue(QuestionResponse::class.java)
            }
        }
    }
}