package com.example.uvshop.UIView.Login.ViewModel

import com.example.uvshop.Data.Product
import com.example.uvshop.Data.Shop
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun getShopByCurrentUser(): Shop? {
    val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email ?: return null
    val db = FirebaseFirestore.getInstance()

    return try {
        // Obtener el documento de la tienda
        val shopQuerySnapshot = db.collection("UVShop")
            .whereEqualTo("reference", currentUserEmail)
            .get()
            .await()

        // Verificar si se encontró alguna tienda
        val shopDocument = shopQuerySnapshot.documents.firstOrNull() ?: return null
        var shop = shopDocument.toObject(Shop::class.java) ?: return null

        // Obtener los productos de la subcolección "POST"
        val productsQuerySnapshot = shopDocument.reference.collection("Post").get().await()
        val products = productsQuerySnapshot.toObjects(Product::class.java)

        // Actualizar la tienda con la lista de productos
        shop = shop.copy(products = products)
        shop
    } catch (e: Exception) {
        // Manejar la excepción
        null
    }
}



