package com.example.uvshop.UIView.PrincipalScreen.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uvshop.R
import com.example.uvshop.UIView.CarouselCard

@Preview(showBackground = true)
@Composable
fun PrincipalView() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        LazyColumn() {
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.bienvenido),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 50.dp, start = 200.dp)
                            .fillMaxWidth()
                            .scale(2.5f)
                    )

                    Text(
                        text = stringResource(id = R.string.usuario),
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .padding(top = 20.dp, start = 270.dp)
                            .fillMaxWidth()
                            .scale(1.5f)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                        .padding(top = 20.dp)
                        .background(color = Color.White)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.white),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    )
                    Column {
                        Text(
                            text = stringResource(id = R.string.novedades),
                            modifier = Modifier.padding(top = 25.dp, start = 48.dp),
                            color = Color.Gray
                        )
                        CarouselCard(null)

                        Text(
                            text = stringResource(id = R.string.favoritos),
                            modifier = Modifier.padding(top = 16.dp, start = 48.dp),
                            color = Color.Gray
                        )
                        CarouselCard(null)

                        Text(
                            text = stringResource(id = R.string.populares),
                            modifier = Modifier.padding(top = 16.dp, start = 48.dp),
                            color = Color.Gray
                        )
                        CarouselCard(null)
                    }
                }
            }
        }
    }
}
