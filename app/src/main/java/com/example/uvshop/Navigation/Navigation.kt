package com.example.uvshop.Navigation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.uvshop.DataBase.Data.DataViewModel
import com.example.uvshop.DataBase.Data.globalVariables
import com.example.uvshop.DataBase.SignIn.GoogleAuthUiClient
import com.example.uvshop.DataBase.SignIn.SignInViewModel
import com.example.uvshop.DataBase.SignIn.UserData
import com.example.uvshop.MainActivity
import com.example.uvshop.UIView.CatalogueScreen.View.CatalogueView
import com.example.uvshop.UIView.Login.View.LoginView
import com.example.uvshop.UIView.MyShopScreen.View.MyShopView
import com.example.uvshop.UIView.PrincipalScreen.View.PrincipalView
import com.example.uvshop.UIView.ProductScreen.View.ProductView
import com.example.uvshop.UIView.SearchScreen.View.SearchView
import com.example.uvshop.UIView.SelectedProductScreen.View.SelectedProductView
import com.example.uvshop.UIView.ShopScreen.View.ShopView
import com.example.uvshop.UIView.UserScreen.View.UserView
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.launch


@Composable
fun NavigationTabs(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    selectedDestination: String,
    navigateTopLevelDestination: (TopLevelDestination) -> Unit,
    googleAuthUiClient: GoogleAuthUiClient,
    lifecycleScope: LifecycleCoroutineScope,
    applicationContext: Context,
    dataViewModel: DataViewModel,
) {
    Row(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = Route.LOGIN,
                modifier = Modifier.weight(1f)
            ) {
                composable(route = Route.LOGIN) {
                    val viewModel = viewModel<SignInViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    LaunchedEffect(key1 = Unit) {
                        if(googleAuthUiClient.getSignedInUser() != null) {

                            navController.navigate(route = Route.PROFILE)
                        }
                    }

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if(result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = googleAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    viewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )

                    LaunchedEffect(key1 = state.isSignInSuccessful) {
                        if(state.isSignInSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Sign in successful",
                                Toast.LENGTH_LONG
                            ).show()

                            navController.navigate("profile")
                            viewModel.resetState()
                        }
                    }

                    LoginView(navController = navController, state = state,
                        onSignInClick = {
                            Log.d("TESTING GOOGLE AUTH","CLICKED")
                            lifecycleScope.launch {
                                val signInIntentSender = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )
                            }
                        }
                    )
                }
                composable(route = Route.HOME) {
                    PrincipalView()
                }

                composable(route = Route.SHOP) {
                    CatalogueView(navController)
                }

                composable(route = Route.PROFILE) {
                    UserView(navController, dataViewModel)
                }
                composable(route = Route.SEARCH) {
                    SearchView(navController, dataViewModel)
                }
                composable(route = Route.REGISTER){
                    ShopView(navController, dataViewModel)
                }
                composable(route = Route.PRODUCT){
                    ProductView(navController, dataViewModel)
                }
                composable(route = Route.MYSHOP){
                    MyShopView(navController)
                }
                composable(route = Route.SELECTED) {
                    SelectedProductView(navController = navController, null)
                }
                composable(
                    route = "selectedProduct/{productName}",
                    arguments = listOf(navArgument("productName") { type = NavType.StringType })
                ) { backStackEntry ->
                    SelectedProductView(
                        navController = navController,
                        productName = backStackEntry.arguments?.getString("productName") ?: ""
                    )
                }
            }

            if (selectedDestination == Route.HOME || selectedDestination == Route.SHOP || selectedDestination == Route.PROFILE) {
                BottomBarNavigation(
                    selectedDestination = selectedDestination,
                    navigateTopLevelDestination = navigateTopLevelDestination
                )
            }
        }
    }
}


@Composable
fun BottomBarNavigation(
    selectedDestination: String,
    navigateTopLevelDestination: (TopLevelDestination) -> Unit
){
    NavigationBar(modifier = Modifier.fillMaxWidth()){
        TOP_LEVEL_DESTINATIONS.forEach{ destinations ->
            NavigationBarItem(
                selected = selectedDestination == destinations.route,
                onClick = { navigateTopLevelDestination(destinations) },
                icon = {
                    Icon(
                        imageVector = destinations.selectedIcon,
                        contentDescription = stringResource(id = destinations.iconTextId))
                },
            )
        }
    }
}