package com.example.delta_task_1.Helpers

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object GameScreen : Screen("game_screen")
    object PlayerSelectScreen: Screen("player_select_screen")



    fun withArgs(args: HashMap<String,String>) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/?${arg.key}=${arg.value}")
            }
        }
    }
}
