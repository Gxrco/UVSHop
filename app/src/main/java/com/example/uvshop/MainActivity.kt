package com.example.uvshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.uvshop.Navigation.NavigationState
import com.example.uvshop.Navigation.Route
import com.example.uvshop.Navigation.TOP_LEVEL_DESTINATIONS
import com.example.uvshop.Navigation.TopLevelDestination
import com.example.uvshop.ui.theme.UVSHopTheme
import com.example.uvshop.Navigation.NavigationTabs

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UVSHopTheme {
                val navController = rememberNavController()
                val navigateAction = remember(navController){
                    NavigationState(navController)
                }
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val selectedDestination = navBackStackEntry?.destination?.route ?: Route.LOGIN

                NavigationTabs(
                    navController = navController,
                    selectedDestination = selectedDestination,
                    navigateTopLevelDestination = navigateAction::navigateTo
                )
            }
        }
    }
}
