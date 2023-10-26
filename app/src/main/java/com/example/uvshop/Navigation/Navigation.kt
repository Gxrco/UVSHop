package com.example.uvshop.Navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uvshop.R
import com.example.uvshop.UIView.CatalogueScreen.View.CatalogueView
import com.example.uvshop.UIView.Login.View.LoginView
import com.example.uvshop.UIView.PrincipalScreen.View.PrincipalView
import com.example.uvshop.UIView.SearchScreen.View.SearchView
import com.example.uvshop.UIView.UserScreen.View.UserView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

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