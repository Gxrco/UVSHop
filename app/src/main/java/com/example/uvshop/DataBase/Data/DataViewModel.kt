package com.example.uvshop.DataBase.Data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DataViewModel: ViewModel(){
    val state = mutableStateOf<List</*ObjetoGERSON*/>>(emptyList())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            state.value = getDataFromFirestore()
        }
    }

    fun addData(post: /*ObjetoGERSON*/){
        viewModelScope.launch {
            addDataToFirestore(post)

            state.value = getDataFromFirestore()
        }
    }
}

suspend fun getDataFromFirestore(): List</*ObjetoGERSON*/> {
    val db = FirebaseFirestore.getInstance()
    var aboutList = emptyList</*ObjetoGERSON*/>()

    try {
        aboutList = db.collection("About")
            .get()
            .await()
            .map { it.toObject(/*ObjetoGERSON*/::class.java) }
    } catch (e: FirebaseFirestoreException) {
        Log.d("Error", "get data from firestore: $e")
    }

    return aboutList
}

suspend fun addDataToFirestore(about: /*ObjetoGERSON*/) {
    val db = FirebaseFirestore.getInstance()

    try {
        db.collection("About").add(about).await()
    } catch (e: FirebaseFirestoreException) {
        Log.d("Error", "add data to firestore: $e")
    }
}