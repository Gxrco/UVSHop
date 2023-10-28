package com.example.uvshop.UIView.Login.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uvshop.Navigation.Route
import com.example.uvshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView( navController: NavController){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        Column(
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(130.dp)
                    .height(90.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
            Box( modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(top = 20.dp)
            ){
                Image(
                    painter = painterResource(id = R.drawable.white),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "UVShop", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = Color.Black, modifier = Modifier.scale(2.5f) )
                    Text(text = "Ingresa para iniciar", modifier = Modifier.padding(top = 12.dp), color = Color.Gray)

                    Text(
                        text = "CORRE INSTITUCIONAL",
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 16.dp),
                        textAlign = TextAlign.Left
                    )
                    TextField(
                        value = username,
                        onValueChange = {},
                        label = { Text("cuenta@uvg.edu.gt") },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 4.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Text(text = "CONTRASEÑA", modifier = Modifier.padding(top = 16.dp), color = Color.Gray)
                    TextField(
                        value = password,
                        onValueChange = {password = it},
                        label = { Text("*******") },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 4.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Button(
                        onClick = {
                                  navController.navigate(Route.HOME)
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(Color.Green)
                    ) {
                        Text(text = "Ingresar", color = Color.White)
                    }

                    Text(
                        text = "¿Olvidaste tu contraseña?",
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .clickable {},
                        color = Color.Gray
                    )

                    Text(
                        text = "Solicita ayuda",
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .clickable {},
                        color = Color.Gray
                    )
                }
            }
        }
    }
}