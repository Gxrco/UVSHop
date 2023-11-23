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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uvshop.DataBase.Data.globalVariables
import com.example.uvshop.Navigation.Route
import com.example.uvshop.R
import com.example.uvshop.UIView.CarouselCard


@Composable
fun CatalogueView(navController: NavController){

    val showDialogCompra = remember { mutableStateOf(false) }
    val showDialogLimpiar = remember { mutableStateOf(false) }



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
                        text = stringResource(id = R.string.mi_carrito),
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
                    .background(Color.White)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.white),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(top = 30.dp, start = 16.dp)
                                .background(Color.LightGray, RoundedCornerShape(16.dp))
                                .clickable {
                                    navController.navigate(Route.SEARCH)
                                }
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
                                    text = stringResource(id = R.string.buscar),
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

                        Text(text = stringResource(id = R.string.en_carrito), modifier = Modifier.padding(top=25.dp, start = 48.dp), color = Color.Gray)
                        CarouselCard(globalVariables.carrito)

                        Divider(modifier = Modifier
                            .padding(horizontal = 48.dp)
                            .fillMaxWidth())

                        Text(text = stringResource(id = R.string.mi_lista_deseos), modifier = Modifier.padding(top=16.dp, start = 48.dp), color = Color.Gray)
                        CarouselCard(null)

                        Row(modifier = Modifier.padding(start = 40.dp)) {
                            Button(
                                onClick = { showDialogCompra.value = true},
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable { },
                                colors = ButtonDefaults.buttonColors(Color.Green)
                            ) {
                                Text(text = stringResource(id = R.string.finalizar_compra), color = Color.White)
                            }

                            Dialog(showDialog = showDialogCompra, title = "Productos agregados!", text = "Los productos han sido encargados en la tienda.")

                            Spacer(modifier = Modifier.width(16.dp))

                            Button(
                                onClick = { globalVariables.carrito = mutableListOf()
                                          showDialogLimpiar.value = true},
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable { },
                                colors = ButtonDefaults.buttonColors(Color.Green)
                            ) {
                                Text(text = stringResource(id = R.string.limpiar_carrito), color = Color.White)
                            }

                            Dialog(showDialog = showDialogLimpiar, title = "Carrito Limpiado!", text = "Los productos agregados han sido eliminados de tu carrito.")

                        }

                    }
                }
            }
        }

    }
}

@Composable
fun Dialog(showDialog: MutableState<Boolean>, title: String, text: String) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(title) },
            text = { Text(text) },
            confirmButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text("OK")
                }
            }
        )
    }
}
