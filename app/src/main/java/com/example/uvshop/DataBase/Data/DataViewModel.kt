package com.example.uvshop.DataBase.Data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uvshop.Data.Product
import com.example.uvshop.Data.Shop
import com.example.uvshop.DataBase.SignIn.UserData
import com.example.uvshop.DataBase.SignIn.UserDataHolder
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


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

    fun addData(shop: Shop?, shopName: String, productName: String){
        viewModelScope.launch {
            addDataToFirestore(shop, shopName, productName)

            state.value = getDataFromFirestore()
        }
    }

    fun fetchAndAssignUserShop(userEmail: String) {
        viewModelScope.launch {
            val userShop = getUserShopFromFirestore(userEmail)
            userShop?.let { shop ->
                UserDataHolder.getInstance().setUserShop(shop = shop)
            }
        }
    }

    private suspend fun getUserShopFromFirestore(userEmail: String): Shop? = withContext(Dispatchers.IO) {
        val db = FirebaseFirestore.getInstance()
        try {
            val querySnapshot = db.collection("UVShop")
                .whereEqualTo("reference", userEmail)
                .get()
                .await()

            if (querySnapshot.documents.isNotEmpty()) {
                querySnapshot.documents.first().toObject<Shop>()
            } else {
                null
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("Error", "get user shop from firestore: $e")
            null
        }
    }
}

suspend fun getDataFromFirestore(): List<Shop> {
    val db = FirebaseFirestore.getInstance()
    var shopList = emptyList<Shop>()

    try {
        val querySnapshot = db.collection("UVShop")
            .get()
            .await()

        shopList = querySnapshot.documents.map { shopDocument ->
            val shop = shopDocument.toObject(Shop::class.java)

            val postCollection = shopDocument.reference.collection("Post").get().await()
            val productList = postCollection.documents.map { postDocument ->
                postDocument.toObject(Product::class.java)
            }

            shop?.copy(products = (productList ?: emptyList()) as List<Product>)
        }.filterNotNull()
    } catch (e: FirebaseFirestoreException) {
        Log.d("Error", "get data from firestore: $e")
    }

    return shopList
}

suspend fun addDataToFirestore(shop: Shop?, shopName: String, productName: String) {
    val db = FirebaseFirestore.getInstance()

    try {
        val data = hashMapOf(
            "name" to shop?.name,
            "category" to shop?.category,
            "description" to shop?.description,
            "reference" to shop?.reference
        )

        db.collection("UVShop").document(shopName).set(data).await()

        val productListData = shop?.products?.map { product ->
            mapOf(
                "name" to product.name,
                "price" to product.price,
                "description" to product.description
            )
        }

        val dontData = hashMapOf(
            "products" to productListData
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
