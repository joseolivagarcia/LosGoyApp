package com.joseoliva.losgoyapp.data.network

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import com.joseoliva.losgoyapp.data.models.QuestionResponse
import com.joseoliva.losgoyapp.data.models.RankingModel
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
    val listaRanking: MutableList<RankingModel> = mutableListOf()
    val task: Task<DataSnapshot> = reference.child(PATH).get()
    val task_ranking: Task<DataSnapshot> = reference.child(PATH_RANKING).limitToLast(5).get()



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

    suspend fun getFirstRanking(): List<RankingModel>{
        task_ranking.addOnSuccessListener {
            dataSnapShot ->
            for(childSnapShot in dataSnapShot.children){
                val objeto: RankingModel? = childSnapShot.getValue(RankingModel::class.java)
                if(objeto != null){
                    listaRanking.add(objeto)
                }
            }
        }.await()
        return listaRanking
    }

    fun getRanking(): Flow<List<RankingModel>> {
        return reference.child(PATH_RANKING).orderByChild("puntos").limitToLast(5).snapshots.map { dataSnapshot: DataSnapshot ->
            dataSnapshot.children.mapNotNull {
                it.getValue(RankingModel::class.java)
            }
        }
    }


}