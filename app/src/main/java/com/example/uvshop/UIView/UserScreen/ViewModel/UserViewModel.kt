package com.example.uvshop.UIView.Login.ViewModel

import com.example.uvshop.DataBase.Data.DataViewModel
import com.example.uvshop.UIView.UserScreen.View.GlobalData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun checkReference(){

    val db = FirebaseFirestore.getInstance()

    db.collection("UVShop")
        .get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                val reference = document.getString("reference")
                if(reference == FirebaseAuth.getInstance().currentUser?.email) {
                    GlobalData.myGlobalVariable = true
                }
            }
        }
        .addOnFailureListener { exception ->
            // Handle any errors here
        }

}

