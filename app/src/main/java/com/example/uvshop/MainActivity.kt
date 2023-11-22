package com.example.uvshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.uvshop.DataBase.Data.DataViewModel
import com.example.uvshop.DataBase.Data.globalVariables
import com.example.uvshop.DataBase.SignIn.GoogleAuthUiClient
import com.example.uvshop.DataBase.SignIn.UserData
import com.example.uvshop.Navigation.NavigationState
import com.example.uvshop.Navigation.NavigationTabs
import com.example.uvshop.Navigation.Route
import com.example.uvshop.ui.theme.UVSHopTheme
import com.google.android.gms.auth.api.identity.Identity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UVSHopTheme {
                val dataViewModel: DataViewModel = viewModel()

                val googleAuthUiClient by lazy {
                    GoogleAuthUiClient(
                        context = applicationContext,
                        oneTapClient = Identity.getSignInClient(applicationContext)
                    )
                }
                globalVariables.listShop = dataViewModel.state.value
                globalVariables.listProducts = globalVariables.listShop.flatMap { it.products.orEmpty() }

                val navController = rememberNavController()
                val navigateAction = remember(navController){
                    NavigationState(navController)
                }
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val selectedDestination = navBackStackEntry?.destination?.route ?: Route.LOGIN

                NavigationTabs(
                    navController = navController,
                    selectedDestination = selectedDestination,
                    navigateTopLevelDestination = navigateAction::navigateTo,
                    googleAuthUiClient = googleAuthUiClient,
                    lifecycleScope = lifecycleScope,
                    applicationContext = applicationContext,
                    dataViewModel = dataViewModel
                )
            }
        }
    }
}
