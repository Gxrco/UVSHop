package com.example.uvshop.UIView.Login.View

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uvshop.DataBase.SignIn.SignInState
import com.example.uvshop.Navigation.Route
import com.example.uvshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(
    navController: NavController,
    state: SignInState,
    onSignInClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        Column() {
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
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(top = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.white),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black,
                        modifier = Modifier.scale(2.5f)
                    )
                    Text(
                        text = stringResource(id = R.string.enter_to_start),
                        modifier = Modifier.padding(top = 12.dp),
                        color = Color.Gray
                    )

                    Text(
                        text = stringResource(id = R.string.institutional_email),
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 16.dp),
                        textAlign = TextAlign.Left,
                    )
                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text(stringResource(id = R.string.email_hint)) },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 4.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Text(
                        text = stringResource(id = R.string.password),
                        modifier = Modifier.padding(top = 16.dp),
                        color = Color.Gray
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(stringResource(id = R.string.password_hint)) },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 4.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Button(
                        onClick = onSignInClick //{
                            //navController.navigate(Route.HOME)
                        /*}*/,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(top = 16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(Color.Green)
                    ) {
                        Text(text = stringResource(id = R.string.login_button_text), color = Color.White)
                    }

                    Text(
                        text = stringResource(id = R.string.forgot_password),
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .clickable { },
                        color = Color.Gray
                    )

                    Text(
                        text = stringResource(id = R.string.request_help),
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .clickable { },
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
