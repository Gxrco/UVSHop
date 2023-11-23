package com.example.uvshop.UIView.Login.ViewModel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

private val storageReference = FirebaseStorage.getInstance().reference.child("Images")

var _selectedImageUri = MutableLiveData<String>()
val selectedImageUri: LiveData<String> get() = _selectedImageUri

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

fun saveImage(uri: Uri?) {
    uri?.let {
        uploadImageToFirebaseStorage(it) { downloadUrl ->
            _selectedImageUri.value = downloadUrl.toString() // Save download URL instead of local URI
        }
    }
}

private fun uploadImageToFirebaseStorage(imageUri: Uri, onSuccess: (Uri) -> Unit) {
    val ref = storageReference.child("images/${System.currentTimeMillis()}.jpg")
    ref.putFile(imageUri)
        .addOnSuccessListener { taskSnapshot ->
            taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener { downloadUri ->
                onSuccess(downloadUri)
            }
        }
        .addOnFailureListener {
            // Handle failure...
        }
}

fun updateShopImage(shopName: String, imageUrl: String) {
    val db = FirebaseFirestore.getInstance()
    val shopRef = db.collection("UVShop").document(shopName)

    shopRef.update("image", imageUrl)
        .addOnSuccessListener {
            // Éxito en la actualización
        }
        .addOnFailureListener {
            // Manejar el error
        }
}
