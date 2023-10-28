package com.example.uvshop.UIView.ShopScreen.View

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
@Preview
@Composable
fun ShopView() {
    var entrepreneurName by remember { mutableStateOf("") }
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
                    Box(Modifier.fillMaxWidth()){
                        Image(
                            painter = painterResource(id = R.drawable.whiteshort),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer { rotationY = 180f }
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "Registro de \n  tu tienda",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                                color = Color.Black,
                                fontSize = 30.sp,
                            )
                            Text(text = "¿Desea iniciar un emprendimiento?",
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
                                    text = "NOMBRE DE EMPRENDIMIENTO",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Left
                                )
                            }
                            TextField(
                                value = entrepreneurName,
                                onValueChange = {},
                                label = { Text("Ingrese el nombre") },
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
                                    text = "CATEGORÍA",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Left
                                )
                            }
                            DropdownMenuOpt("Selecciona la categoría")
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
                                onValueChange = {},
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 4.dp)
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                            )
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(0.9f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Checkbox(
                                    checked = checked,
                                    onCheckedChange = { newCheckedState ->
                                        checked = newCheckedState
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color.DarkGray,
                                        uncheckedColor = Color.Gray,
                                        checkmarkColor = Color.LightGray
                                    ),
                                    modifier = Modifier
                                        .size(40.dp) // Adjust the size as needed
                                        .clip(CircleShape) // Apply the circular shape modifier
                                )
                                Text(text = "Estoy de acuerdo con cumplir las normas \ndentro de la aplicación",
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Light),
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,
                                    lineHeight = 15.sp,
                                )
                            }
                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 12.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFF0BA259))
                            ) {
                                Text(
                                    text = "Registrar",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuOpt(
    Description: String
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var category by remember {
        mutableStateOf("")
    }

    val genderOptions = listOf("Tecnologia","Comida","Belleza","Servicio")

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { newValue ->
            isExpanded = newValue
        }
    ) {
        TextField(
            value = category,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            placeholder = {
                Text(text = Description)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(0.8f)
                .padding(top = 4.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            genderOptions.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(text = option)
                    },
                    onClick = {
                        category = option
                        isExpanded = false
                    }
                )
            }
        }
    }
}
