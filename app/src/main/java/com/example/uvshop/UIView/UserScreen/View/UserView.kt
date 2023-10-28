package com.example.uvshop.UIView.UserScreen.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.uvshop.Navigation.Route
import com.example.uvshop.R
import com.example.uvshop.UIView.CarouselCard

@Composable
fun UserView(navController: NavController){
    val addresses = listOf("HOGAR", "UNIVERSIDAD", "HOTEL", "PARQUE", "CIUDAD", "OFICINA")
    LazyColumn(){
        item {
            Box(){
                Image(
                    painter = painterResource(id = R.drawable.fondo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                )
                Box(modifier = Modifier
                    .size(130.dp)
                    .align(Alignment.TopCenter)
                    .offset(0.dp, 80.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .zIndex(1f)
                    .border(BorderStroke(3.dp, Color.LightGray), shape = RoundedCornerShape(15.dp))){
                    Image(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = "Foto de perfil",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(Modifier.fillMaxSize()) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)){
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
                    Spacer(modifier = Modifier.height(70.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        Spacer(modifier = Modifier.height(60.dp))
                        Text(text = "JUAN CARLOS BODOQUE",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black,
                            fontSize = 20.sp,
                        )
                        Button(
                            onClick = {
                                if (GlobalData.myGlobalVariable){
                                    navController.navigate(route = Route.MYSHOP)

                                }else{
                                    navController.navigate(route = Route.REGISTER)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(12 / 23f)
                                .padding(top = 16.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF7ED957))
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Star,
                                contentDescription = "Gestionar tienda",
                                tint = Color.White,
                                modifier = Modifier.padding(end = 5.dp)
                            )
                            Text(
                                text = "Gestionar mi tienda",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Divider(thickness = 2.dp, color = Color(0xFFF3F3F3), modifier = Modifier.fillMaxWidth(21/23f))
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                            Text(
                                text = "MIS PEDIDOS",
                                color = Color.Black,
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp,
                                letterSpacing = 1.sp,
                                modifier = Modifier.padding(start = 20.dp )
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        CarouselCard()
                        Spacer(modifier = Modifier.height(30.dp))
                        AddressesList(AddressList = addresses)
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
            }
        }
    }

}

@Composable
fun AddressesList(AddressList: List<String>){
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
    Text(
        text = "DIRECCIONES",
        color = Color.Black,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        letterSpacing = 1.sp,
        modifier = Modifier.padding(start = 20.dp )
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Divider(thickness = 2.dp, color = Color(0xFFF3F3F3), modifier = Modifier.fillMaxWidth(21/23f))

    for (str in AddressList) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(start = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = "Ubicacion",
                tint = Color.LightGray
            )
            Text(
                text = str,
                color = Color(0xFF8F8E8E),
                fontWeight = FontWeight.Light,
                fontSize = 15.sp,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider(thickness = 1.dp, color = Color(0xFFF3F3F3), modifier = Modifier.fillMaxWidth(21/23f))
    }
}

object GlobalData {
    var myGlobalVariable: Boolean = false
}


