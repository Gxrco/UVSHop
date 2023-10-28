package com.example.uvshop.Navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uvshop.UIView.CatalogueScreen.View.CatalogueView
import com.example.uvshop.UIView.Login.View.LoginView
import com.example.uvshop.UIView.PrincipalScreen.View.PrincipalView
import com.example.uvshop.UIView.SearchScreen.View.SearchView
import com.example.uvshop.UIView.UserScreen.View.UserView


@Composable
fun NavigationTabs(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    selectedDestination: String,
    navigateTopLevelDestination: (TopLevelDestination) -> Unit,
) {
    Row(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = Route.LOGIN,
                modifier = Modifier.weight(1f)
            ) {
                composable(route = Route.LOGIN) {
                    LoginView(navController = navController)
                }
                composable(route = Route.HOME) {
                    PrincipalView()
                }

                composable(route = Route.SHOP) {
                    CatalogueView(navController)
                }

                composable(route = Route.PROFILE) {
                    UserView()
                }
                composable(route = Route.SEARCH) {
                    SearchView(navController)
                }
            }

            if (selectedDestination != Route.LOGIN && selectedDestination!= Route.SEARCH) {
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