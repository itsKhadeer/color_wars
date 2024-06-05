package com.example.delta_task_1.Helpers

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.delta_task_1.JPC_Screens.GameScreen
import com.example.delta_task_1.JPC_Screens.HomeScreen
import com.example.delta_task_1.JPC_Screens.PlayerSelectScreen
import com.example.delta_task_1.ViewModels.GameViewModel

@Composable
fun UI(viewModel: GameViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.GameScreen.route+"/?player1Name={player1Name}/?player2Name={player2Name}",
            arguments = listOf(
                navArgument("player1Name"){
                    type = NavType.StringType
                    defaultValue = "Player 1"
                    nullable = true
                },
                navArgument("player2Name"){
                    type = NavType.StringType
                    defaultValue = "Player 2"
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            var player1Name = "Player 1"
            var player2Name = "Player 2"
            navBackStackEntry.arguments?.getString("player1Name")?.let {
                if (it != "") { player1Name = it}
            }
            navBackStackEntry.arguments?.getString("player2Name")?.let {
                if (it != "") { player2Name = it }
            }

            GameScreen(player1Name = player1Name, player2Name = player2Name, viewModel = viewModel, navController = navController )
        }
        composable(route = Screen.PlayerSelectScreen.route) {
            PlayerSelectScreen(navController)
        }


    }
}



