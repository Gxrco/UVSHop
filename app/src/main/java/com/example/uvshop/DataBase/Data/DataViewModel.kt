package com.example.uvshop.DataBase.Data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uvshop.Data.Product
import com.example.uvshop.Data.Shop
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


object globalVariables: ViewModel(){
    var listShop by mutableStateOf<List<Shop>>(emptyList())
    var listProducts by mutableStateOf<List<Product>>(emptyList())
}

class DataViewModel: ViewModel(){
    val state = mutableStateOf<List<Shop>>(emptyList())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            state.value = getDataFromFirestore()
        }
    }

    fun addData(shop: Shop, shopName: String, productName: String){
        viewModelScope.launch {
            addDataToFirestore(shop, shopName, productName)

            state.value = getDataFromFirestore()
        }
    }
}

suspend fun getDataFromFirestore(): List<Shop> {
    val db = FirebaseFirestore.getInstance()
    var shopList = emptyList<Shop>()

    try {
        shopList = db.collection("UVShop")
            .get()
            .await()
            .map { it.toObject(Shop::class.java) }
    } catch (e: FirebaseFirestoreException) {
        Log.d("Error", "get data from firestore: $e")
    }

    return shopList
}

suspend fun addDataToFirestore(shop: Shop, shopName: String, productName: String) {
    val db = FirebaseFirestore.getInstance()

    try {
        val data = hashMapOf(
            "name" to shop.name,
            "category" to shop.category,
            "description" to shop.description,
            "reference" to shop.reference
        )

        db.collection("UVShop").document(shopName).set(data).await()

        val dontData = hashMapOf(
            "products" to shop.products
        )

        db.collection("UVShop").document(shopName).collection("Post").document(productName).set(dontData).await()
    } catch (e: FirebaseFirestoreException) {
        Log.d("Error", "add data to firestore: $e")
    }
}

suspend fun updateProductsInFirestore(shopName: String, newProduct: Product) {
    val db = FirebaseFirestore.getInstance()

    try {
        val productMap = mapOf(
            "name" to newProduct.name,
            "price" to newProduct.price,
            "description" to newProduct.description
        )

        db.collection("UVShop").document(shopName).collection("Post").document(newProduct.name.toString()).update(productMap)
            .await()
    } catch (e: FirebaseFirestoreException) {
        Log.d("Error", "update products in firestore: $e")
    }
}
