package com.example.uvshop.UIView.SearchScreen.View

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uvshop.DataBase.Data.DataViewModel
import com.example.uvshop.DataBase.Data.globalVariables
import com.example.uvshop.R
import com.example.uvshop.UIView.CarouselCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(navController: NavController, dataViewModel: DataViewModel){
    var item by remember { mutableStateOf("") }
    var recentWords by remember { mutableStateOf(listOf<String>()) }
    var searchQuery by remember { mutableStateOf("") }

    // Assuming 'dataViewModel.state.value' is a list of products
    val filteredProducts = globalVariables.listProducts.filter { product ->
        product.name.contains(searchQuery, ignoreCase = true)
    }




    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.darkgreen),
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
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .padding(top = 1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                    TextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                            val words = it.split(" ")
                            if (it.endsWith(" ")) {
                                item = it.trimEnd()
                            }

                            if (it.endsWith("\n") && words.isNotEmpty()) {
                                val newWord = words.last().trim()
                                if (newWord.isNotBlank()) {
                                    recentWords = (recentWords + newWord).takeLast(8)
                                    item = ""
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                            }
                        ),
                        label = { Text(stringResource(id = R.string.buscar)) },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 20.dp, start = 50.dp)
                            .clip(RoundedCornerShape(16.dp))
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
                    if(searchQuery.isEmpty()){
                        Column {
                            Divider(
                                modifier = Modifier
                                    .padding(top = 32.dp)
                                    .padding(horizontal = 48.dp)
                                    .fillMaxWidth()
                            )

                            Text(
                                text = stringResource(id = R.string.recientes),
                                modifier = Modifier.padding(top = 5.dp, start = 48.dp),
                                color = Color.Gray
                            )
                            Row(
                                modifier = Modifier
                                    .padding(top = 8.dp)
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally),
                            ) {
                                val (leftColumn, rightColumn) = recentWords.chunked(4).let { chunks ->
                                    if (chunks.size >= 2) {
                                        chunks[0] to chunks[1]
                                    } else {
                                        chunks.getOrNull(0) to emptyList()
                                    }
                                }
                                Column(
                                    modifier = Modifier.width(120.dp)
                                ) {
                                    if (leftColumn != null) {
                                        leftColumn.forEach { word ->
                                            Text(
                                                text = word,
                                                modifier = Modifier.padding(
                                                    start = 48.dp,
                                                    top = 8.dp,
                                                    end = 8.dp
                                                ),
                                                color = Color.Green
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.width(24.dp))

                                Column (modifier = Modifier.width(120.dp)) {
                                    rightColumn.forEach { word ->
                                        Text(
                                            text = word,
                                            modifier = Modifier.padding(
                                                start = 48.dp,
                                                top = 8.dp,
                                                end = 8.dp
                                            ),
                                            color = Color.Green
                                        )
                                    }
                                }
                            }

                            Divider(
                                modifier = Modifier
                                    .padding(top = 32.dp)
                                    .padding(horizontal = 48.dp)
                                    .fillMaxWidth()
                            )

                            Text(
                                text = stringResource(id = R.string.recomendados),
                                modifier = Modifier.padding(top = 16.dp, start = 48.dp, bottom = 32.dp),
                                color = Color.Gray
                            )
                            CarouselCard(null)
                        }
                    }
                    else{
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            filteredProducts.forEach { product ->
                                Text(
                                    text = product.name,
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .padding(vertical = 8.dp)
                                        .clickable {
                                            navController.navigate("selectedProduct/${product.name}")
                                        }
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}
