package com.example.uvshop.UIView.CatalogueScreen.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uvshop.R
import com.example.uvshop.UIView.CarouselCard


@Preview(showBackground = true)
@Composable
fun CatalogueView(){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        LazyColumn(){
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "MI CARRITO",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 100.dp, start = 180.dp)
                            .fillMaxWidth()
                            .scale(1.9f)
                    )
                }
                Box( modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(top = 20.dp)
                    .background(color = Color.White)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.white),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    )
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(top = 30.dp, start = 16.dp)
                                .background(Color.LightGray, RoundedCornerShape(16.dp))
                                .clickable { }
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .scale(1.4f),
                                    colorFilter = ColorFilter.tint(Color.Gray)
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Text(
                                    text = "BUSCAR",
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically),
                                    color = Color.Gray
                                )
                            }
                        }

                        Divider(modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(horizontal = 48.dp)
                            .fillMaxWidth())

                        Text(text = "EN CARRITO", modifier = Modifier.padding(top=25.dp, start = 48.dp), color = Color.Gray)
                        CarouselCard()

                        Divider(modifier = Modifier
                            .padding(horizontal = 48.dp)
                            .fillMaxWidth())

                        Text(text = "MI LISTA DE DESEOS", modifier = Modifier.padding(top=16.dp, start = 48.dp), color = Color.Gray)
                        CarouselCard()

                        Row(modifier = Modifier.padding(start = 40.dp)) {
                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable {  },
                                colors = ButtonDefaults.buttonColors(Color.Green)
                            ) {
                                Text(text = "Finalizar Compra", color = Color.White)
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable {  },
                                colors = ButtonDefaults.buttonColors(Color.Green)
                            ) {
                                Text(text = "Limpiar Carrito", color = Color.White)
                            }
                        }

                    }
                }
            }
        }

    }
}
