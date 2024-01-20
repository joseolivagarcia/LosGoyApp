package com.joseoliva.losgoyapp.data.network

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import com.joseoliva.losgoyapp.data.models.QuestionResponse
import com.joseoliva.losgoyapp.data.models.rankingModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseService @Inject constructor( private val reference: DatabaseReference) {

    companion object{
        private const val PATH = "preguntas"
        private const val PATH_RANKING = "ranking"
    }

    val lista: MutableList<QuestionResponse> = mutableListOf()
    val listaRanking: MutableList<rankingModel> = mutableListOf()
    val task: Task<DataSnapshot> = reference.child(PATH).get()
    val task_ranking: Task<DataSnapshot> = reference.child(PATH_RANKING).get()



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

    fun getRanking(): Flow<List<rankingModel>> {
        return reference.child(PATH_RANKING).orderByChild("puntos").snapshots.map { dataSnapshot: DataSnapshot ->
            dataSnapshot.children.mapNotNull {
                it.getValue(rankingModel::class.java)
            }
        }
    }

    /*fun getPreguntas(): List<QuestionResponse> {
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
    }*/

    /*suspend fun obtenerPreguntas(): List<QuestionResponse>{
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
    }*/

}