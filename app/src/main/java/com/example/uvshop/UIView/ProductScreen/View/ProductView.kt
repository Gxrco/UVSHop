package com.example.uvshop.UIView.ProductScreen.View


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uvshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductView() {

    var product by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }

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
                Column(Modifier.fillMaxSize()) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                    ) {
                        //Back Button
                        IconButton(
                            onClick = {},
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
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(
                                shape = RoundedCornerShape(
                                    topStartPercent = 15,
                                    topEndPercent = 15
                                ),
                                color = Color.White
                            ))
                    {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "Agregar\nProducto",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                                color = Color.Black,
                                fontSize = 30.sp,
                            )
                            Text(text = "¡Añade un nuevo producto a tu catálogo!",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Light),
                                color = Color.Black,
                                fontSize = 15.sp,
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(
                                Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 16.dp), horizontalArrangement = Arrangement.Start) {
                                Text(
                                    text = "NOMBRE DE PRODUCTO*",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Left
                                )
                            }
                            TextField(
                                value = product,
                                onValueChange = {},
                                label = { Text("Ingrese el nombre del producto") },
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 4.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            Row(
                                Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 16.dp), horizontalArrangement = Arrangement.Start) {
                                Text(
                                    text = "PRECIO*",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Left
                                )
                            }
                            TextField(
                                value = price,
                                onValueChange = {},
                                label = { Text("Ingrese el precio del producto") },
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 4.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            Row(
                                Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 16.dp), horizontalArrangement = Arrangement.Start) {
                                Text(
                                    text = "DESCRIPCIÓN",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Left
                                )
                            }
                            TextField(
                                value = description,
                                label = { Text("Escribe una breve descripción...") },
                                onValueChange = {},
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 4.dp)
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                            )
                            Row(
                                Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 16.dp), horizontalArrangement = Arrangement.Start) {
                                Text(
                                    text = "ADJUNTAR IMÁGENES",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Left
                                )
                            }
                            Row(modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .background(
                                    shape = RoundedCornerShape(16.dp),
                                    color = Color(0xFFE6E0ED)
                                )
                                .height(100.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Box(modifier = Modifier
                                    .background(shape = RoundedCornerShape(20), color = Color(0xFFCCC4D6))
                                    .size(80.dp),
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Add,
                                        contentDescription = "Cargar nueva imagen",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .fillMaxSize(0.8f)
                                    )
                                }
                                Spacer(Modifier.width(15.dp))
                                Box(modifier = Modifier
                                    .background(shape = RoundedCornerShape(20), color = Color(0xFFCCC4D6))
                                    .size(80.dp),
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Add,
                                        contentDescription = "Cargar nueva imagen",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .fillMaxSize(0.8f)
                                    )
                                }
                                Spacer(Modifier.width(15.dp))
                                Box(modifier = Modifier
                                    .background(shape = RoundedCornerShape(20), color = Color(0xFFCCC4D6))
                                    .size(80.dp),
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Add,
                                        contentDescription = "Cargar nueva imagen",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .fillMaxSize(0.8f)
                                    )
                                }

                            }
                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 20.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFF0BA259))
                            ) {
                                Text(
                                    text = "Añadir producto",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(40.dp))
                        }
                    }
                }
            }
        }
    }
}
