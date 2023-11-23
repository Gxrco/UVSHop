package com.example.uvshop.UIView.SelectedProductScreen.View

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.uvshop.DataBase.Data.globalVariables
import com.example.uvshop.R

@Composable
fun SelectedProductView(navController: NavController, productName: String?){

    val product = globalVariables.listProducts.filter { it.name == productName }
    val showDialog = remember { mutableStateOf(false) }


    Box(Modifier.fillMaxSize()){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ){
            item{
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = product.get(0).name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
                Spacer(Modifier.height(25.dp))
                Text(
                    text = product.get(0).description,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(horizontal = 15.dp),

                    )
            }
        }
        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Favorito",
                tint = Color.White
            )
        }
        Box(modifier = Modifier
            .shadow(elevation = 10.dp, spotColor = Color.Black)
            .align(Alignment.BottomCenter)
            .fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF1F1F1))
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = "Q${product.get(0).price}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Button(
                    onClick = { globalVariables.carrito.add(product[0])
                              showDialog.value = true
                              },
                    shape = RoundedCornerShape(10),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0BA259))
                ) {
                    Text(
                        text = stringResource(id = R.string.anadiralcarrito),
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }

                Dialog(showDialog = showDialog, title = "Producto Agregado!", text = "El producto ha sido agregado a tu carrito.", navController)
            }
        }
    }
}

@Composable
fun Dialog(showDialog: MutableState<Boolean>, title: String, text: String, navController: NavController) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { androidx.compose.material3.Text(title) },
            text = { androidx.compose.material3.Text(text) },
            confirmButton = {
                Button(onClick = { showDialog.value = false
                    navController.popBackStack()}) {
                    androidx.compose.material3.Text("OK")
                }
            }
        )
    }
}