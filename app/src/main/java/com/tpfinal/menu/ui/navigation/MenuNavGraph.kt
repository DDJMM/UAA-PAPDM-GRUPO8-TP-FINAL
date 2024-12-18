package com.tpfinal.menu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tpfinal.menu.ui.home.HomeDestination
import com.tpfinal.menu.ui.home.InicioScreen
import com.tpfinal.menu.ui.menu.MenuDetailsDestination
import com.tpfinal.menu.ui.menu.MenuEntryDestination

@Composable
fun MenuNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            InicioScreen(
                navigateToMenuEntry = { navController.navigate(MenuEntryDestination.route) },
                navigateToMenuUpdate = {
                    navController.navigate("${MenuDetailsDestination.route}/${it}")
                }
            )
        }
    }

}