package com.example.uvshop.UIView.Login.ViewModel

import com.example.uvshop.UIView.UserScreen.View.GlobalData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore



    fun getShopNameByEmail(currentUserEmail: String?, onResult: (String?) -> Unit) {

        val db = FirebaseFirestore.getInstance()
        val currentUserEmail = currentUserEmail
        var shopName = ""

        if (currentUserEmail == null) {
            onResult(null)
            return
        }

        db.collection("UVShop")
            .whereEqualTo("reference", currentUserEmail)
            .get()
            .addOnSuccessListener { documents ->
                var shopName: String? = null
                if (!documents.isEmpty) {
                    for (document in documents) {
                        shopName = document.getString("name")
                        if (shopName != null) {
                            break
                        }
                    }
                }
                onResult(shopName)
            }
            .addOnFailureListener { exception ->
                // Handle any errors here
                onResult(null)
            }
    }
