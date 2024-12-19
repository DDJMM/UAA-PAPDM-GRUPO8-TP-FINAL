package com.tpfinal.menu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tpfinal.menu.ui.home.HomeDestination
import com.tpfinal.menu.ui.home.InicioScreen
import com.tpfinal.menu.ui.menu.MenuDetailsDestination
import com.tpfinal.menu.ui.menu.MenuDetailsScreen
import com.tpfinal.menu.ui.menu.MenuEditDestination
import com.tpfinal.menu.ui.menu.MenuEditScreen
import com.tpfinal.menu.ui.menu.MenuEntryDestination
import com.tpfinal.menu.ui.menu.MenuEntryScreen

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

        composable(route = MenuEntryDestination.route) {
            MenuEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = MenuDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(MenuDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            MenuDetailsScreen(
                navigateToEditItem = { navController.navigate("${MenuEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = MenuEditDestination.routeWithArgs,
            arguments = listOf(navArgument(MenuEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            MenuEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }

}