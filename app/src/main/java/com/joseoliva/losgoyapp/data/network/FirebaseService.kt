package com.joseoliva.losgoyapp.data.network

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import com.joseoliva.losgoyapp.data.response.QuestionResponse
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseService @Inject constructor( private val reference: DatabaseReference) {

    companion object{
        private const val PATH = "preguntas"
    }

    val lista: MutableList<QuestionResponse> = mutableListOf()
    val task: Task<DataSnapshot> = reference.child(PATH).get()

    fun getPreguntas(): List<QuestionResponse> {
        reference.child(PATH).snapshots.map { dataSnapshot: DataSnapshot ->
            dataSnapshot.children.mapNotNull {
                for (p in it.children){
                    val pregunta: QuestionResponse? = p.getValue(QuestionResponse::class.java)
                    if(pregunta != null){
                    lista.add(pregunta)
                        Log.i("preguntas", "Hay ${lista.size} preguntas")
                    }
                }
            }
        }
        return lista
    }

    suspend fun getAllPreguntas(): List<QuestionResponse>{
        task.addOnSuccessListener {
            dataSnapShot ->
            for(childSnapshot in dataSnapShot.children){
                val objeto: QuestionResponse? = childSnapshot.getValue(QuestionResponse::class.java)
                if(objeto != null){
                    lista.add(objeto)
                }
            }
        }.await()
        return lista
    }

    suspend fun obtenerPreguntas(): List<QuestionResponse>{
        try {
            val querySnapShot = reference.child(PATH).get().await()
            for (doc in querySnapShot.children){
                val objeto: QuestionResponse? = doc.getValue(QuestionResponse::class.java)
                if(objeto != null){
                    lista.add(objeto)
                }
            }
        }catch (e: Exception){
            Log.i("error", "Error al obtener datos ${e.message}")
        }
        return lista
    }

}