package com.example.uvshop.UIView.MyShopScreen.View

import android.content.ContentValues
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.uvshop.Data.Shop
import com.example.uvshop.Navigation.Route
import com.example.uvshop.R
import com.example.uvshop.UIView.Login.ViewModel._selectedImageUri
import com.example.uvshop.UIView.Login.ViewModel.getShopByCurrentUser
import com.example.uvshop.UIView.Login.ViewModel.saveImage
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@Composable
fun MyShopView(navController: NavController) {

    val context = LocalContext.current
    var takenImage by remember { mutableStateOf<Bitmap?>(null) }
    val takePictureLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
            if (bitmap != null) {
                println("Success: Bitmap received.")
                takenImage = bitmap
                // Convert Bitmap to Uri and save it in your ViewModel
                val uri = bitmap.let { imageBitmap ->
                    val values = ContentValues().apply {
                        // Ensure unique file name
                        put(
                            MediaStore.Images.Media.DISPLAY_NAME,
                            "Title${System.currentTimeMillis()}.jpg"
                        )
                        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                    }

                    val contentResolver = context.contentResolver
                    val imageUri =
                        contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

                    imageUri?.let {
                        contentResolver.openOutputStream(it)?.use { outputStream ->
                            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        }
                    }
                    imageUri
                }
                saveImage(uri)
            } else {
                println("Error: Bitmap is null.")
            }
        }


    var shop by remember { mutableStateOf<Shop?>(null) }
    val scope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        scope.launch {
            shop = getShopByCurrentUser()
        }
    }

    Box(Modifier.fillMaxSize()) {
        shop?.let { currentShop ->
            val painter = when {
                takenImage != null -> remember { BitmapPainter(takenImage!!.asImageBitmap()) } // Para Bitmap
                currentShop.image != null -> rememberAsyncImagePainter(model = currentShop.image) // Para imagen de internet
                else -> painterResource(id = R.drawable.person) // Imagen por defecto
            }
            LazyColumn() {
                item {
                    Box() {
                        Image(
                            painter = painterResource(id = R.drawable.fondoshort),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                        )
                        Box(
                            modifier = Modifier
                                .size(130.dp)
                                .align(Alignment.TopCenter)
                                .offset(100.dp, 60.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .zIndex(1f)
                                .border(
                                    BorderStroke(3.dp, Color(0xFFF3F3F3)),
                                    shape = RoundedCornerShape(15.dp)
                                )
                        ) {
                            Image(
                                painter = painter,
                                contentDescription = "Foto de perfil",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(modifier = Modifier.size(30.dp).zIndex(1f), contentAlignment = Alignment.TopEnd){
                               IconButton(
                                   onClick = {
                                       val userUpdates = mapOf(
                                           "image" to (_selectedImageUri.value ?: "")
                                       )

                                       takePictureLauncher.launch()
                                       FirebaseFirestore.getInstance().collection("UVSHop").document(currentShop.name).update(userUpdates)
                                             },
                                   modifier = Modifier.fillMaxSize()) {
                                   Icon(imageVector = Icons.Default.Camera, contentDescription = "")
                               }
                            }
                        }
                        Column(Modifier.fillMaxSize()) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .background(Color.Transparent)
                            ) {
                                // Back Button
                                IconButton(
                                    onClick = {
                                        navController.popBackStack()
                                    },
                                    modifier = Modifier
                                        .padding(30.dp)
                                        .size(35.dp)

                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Back",
                                        modifier = Modifier.fillMaxSize(),
                                        tint = Color.White
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(30.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White)
                            ) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Row(
                                    Modifier
                                        .fillMaxWidth(0.85f)
                                        .padding(top = 25.dp),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Text(
                                        text = shop?.name.toString(),
                                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                        color = Color.Black,
                                        fontSize = 25.sp,
                                    )
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(0.85f)
                                        .background(
                                            shape = RoundedCornerShape(16.dp),
                                            color = Color(0xFFE6E0ED)
                                        )
                                        .height(110.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(Modifier.padding(20.dp)) {
                                        Text(
                                            text = shop?.category.toString(),
                                            color = Color.DarkGray
                                        )
                                        Text(
                                            text = shop?.description.toString(),
                                            textAlign = TextAlign.Justify,
                                            color = Color.DarkGray
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(25.dp))
                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.productos),
                                        color = Color.Black,
                                        fontWeight = FontWeight.Light,
                                        fontSize = 12.sp,
                                        letterSpacing = 1.sp,
                                        modifier = Modifier.padding(start = 28.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Divider(
                                    thickness = 2.dp,
                                    color = Color(0xFFF3F3F3),
                                    modifier = Modifier.fillMaxWidth(0.85f)
                                )
                                currentShop.products.chunked(2).forEach { pair ->
                                    Row(
                                        modifier = Modifier.padding(20.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        pair.forEachIndexed { index, it ->
                                            Box(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .clip(RoundedCornerShape(15.dp))
                                                    .background(Color.LightGray)
                                                    .height(160.dp)
                                                    .clickable {
                                                        navController.navigate(route = "selectedProduct/${it.name}")
                                                    }
                                            ) {
                                                Image(
                                                    painter = painterResource(
                                                        id = R.drawable.product
                                                    ),
                                                    contentDescription = "",
                                                    contentScale = ContentScale.Crop
                                                )
                                                Text(
                                                    text = it.price,
                                                    modifier = Modifier
                                                        .align(Alignment.BottomStart)
                                                        .background(Color(0xFF0BA259))
                                                        .padding(8.dp)
                                                        .fillMaxWidth(),
                                                    fontSize = 16.sp,
                                                    color = Color.White,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }

                                            if (index == 0) {
                                                Spacer(modifier = Modifier.width(10.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                navController.navigate(route = Route.PRODUCT)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            contentColor = Color.White,
            containerColor = Color(0xFF0BA259)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }
}


