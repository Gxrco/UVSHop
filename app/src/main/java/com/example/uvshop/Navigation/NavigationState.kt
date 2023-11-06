package com.example.uvshop.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.uvshop.R

class NavigationState(private val navController: NavHostController){
    fun navigateTo(destination: TopLevelDestination){
        navController.navigate(destination.route){
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
        }
    }
}

data class TopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val iconTextId: Int
)

val TOP_LEVEL_DESTINATIONS = listOf(
    TopLevelDestination(
        route = Route.HOME,
        selectedIcon = Icons.Rounded.Home,
        iconTextId = R.string.home
    ),
    TopLevelDestination(
        route = Route.SHOP,
        selectedIcon = Icons.Rounded.ShoppingCart,
        iconTextId = R.string.shop
    ),
    TopLevelDestination(
        route = Route.PROFILE,
        selectedIcon = Icons.Rounded.Person,
        iconTextId = R.string.profile
    )
)

object Route{
    const val LOGIN = "login"
    const val HOME = "home"
    const val SHOP = "shop"
    const val PROFILE = "profile"
    const val SEARCH = "search"
    const val PRODUCT = "product"
    const val MYSHOP = "myshop"
    const val REGISTER = "register"
    const val SELECTED = "selected"
}