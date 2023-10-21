package com.example.uvshop.Navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uvshop.BottomBarNavigation
import com.example.uvshop.UIView.CatalogueScreen.View.CatalogueView
import com.example.uvshop.UIView.Login.View.LoginView
import com.example.uvshop.UIView.PrincipalScreen.View.PrincipalView


@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    selectedDestination: String,
    navigateTopLevelDestination: (TopLevelDestination) -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = Route.HOME,
                modifier = Modifier.weight(1f)
            ) {

                composable(route = Route.HOME) {
                    PrincipalView()
                }


                composable(route = Route.SHOP) {
                    CatalogueView()
                }


                composable(route = Route.PROFILE) {
                    LoginView()
                }
            }
            BottomBarNavigation(
                selectedDestination = selectedDestination,
                navigateTopLevelDestination = navigateTopLevelDestination)
        }
    }

}