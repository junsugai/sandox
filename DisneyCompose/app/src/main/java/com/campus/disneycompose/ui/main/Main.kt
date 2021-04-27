package com.campus.disneycompose.ui.main

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.campus.disneycompose.ui.details.PosterDetails
import com.campus.disneycompose.ui.posters.Posters
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun DisneyMain() {
    val navController = rememberNavController()
    val context = LocalContext.current

    ProvideWindowInsets {
        NavHost(
            navController = navController,
            startDestination = NavScreen.Home.route
        ) {
            composable(NavScreen.Home.route) { backStackEntry ->
                val viewModel = hiltNavGraphViewModel<MainViewModel>(
                    backStackEntry = backStackEntry
                )

                Posters(
                    viewModel = viewModel,
                    selectPoster = {
                        navController.navigate("${NavScreen.PosterDetails.route}/$it")
                    }
                )
                viewModel.toast.observe(LocalLifecycleOwner.current) {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            composable(
                route = NavScreen.PosterDetails.routeWithArgument,
                arguments = listOf(
                    navArgument(
                        NavScreen.PosterDetails.argument0
                    ) { type = NavType.LongType }
                )
            ) { backStackEntry ->
                val viewModel =
                    hiltNavGraphViewModel<MainViewModel>(
                        backStackEntry = backStackEntry
                    )

                val posterId =
                    backStackEntry.arguments?.getLong(
                        NavScreen.PosterDetails.argument0
                    ) ?: return@composable

                viewModel.getPoster(posterId)

                PosterDetails(viewModel = viewModel) {
                    navController.popBackStack(navController.graph.startDestination, false)
                }
            }
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object PosterDetails : NavScreen("PosterDetails") {

        const val routeWithArgument: String = "PosterDetails/{posterId}"

        const val argument0: String = "posterId"
    }
}
