package com.example.delta_task_1.JPC_Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.delta_task_1.Helpers.PreferencesManager
import com.example.delta_task_1.ViewModels.GameViewModel

@Composable
fun GameScreen(
    player1Name: String,
    player2Name: String,
    viewModel: GameViewModel,
    navController: NavController
) {
    val context = LocalContext.current;
    val preferencesManager = remember { PreferencesManager(context) }
    val red_wins = preferencesManager.getData("red_wins", "0");
    val blue_wins = preferencesManager.getData("blue_wins", "0")
    var openDialog by remember { mutableStateOf(false) }
    if(openDialog) Winner_dialog(
        setShowDialog = {openDialog = it},
        winner = if(viewModel.check_win_condition() == "Player1") player1Name else player2Name,
        winner_color = viewModel.backgroundColor,
        viewModel = viewModel,
        navController
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = viewModel.backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "${viewModel.get_player2_score()}",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentWidth()
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(
                        color = Color(0xFF2FB6F0),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .width(50.dp)
                    .padding(10.dp)
                    .rotate(180f),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(60.dp))

            Text(
                text = player2Name,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(
                        color = Color(0xFF2FB6F0),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .width(150.dp)
                    .padding(10.dp)
                    .rotate(180f),
                textAlign = TextAlign.Center
            )
        }

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxHeight(0.5f),
            columns = GridCells.Fixed(viewModel.no_of_columns),
            content =
            {
                items(viewModel.Player1_Arr.size) {i ->
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(5.dp))
                            .clickable {
                                viewModel.on_click(i)
                                openDialog = viewModel.wins
                                if(viewModel.check_win_condition() == "Player1") {
                                    val new_red_wins = Integer.parseInt(red_wins) + 1
                                    preferencesManager.saveData("red_wins", new_red_wins.toString())
                                }
                                if(viewModel.check_win_condition() == "Player2") {
                                    val new_blue_wins = Integer.parseInt(blue_wins) + 1
                                    preferencesManager.saveData("blue_wins", new_blue_wins.toString())
                                }
                            }
                            .background(color = Color(0x75FFFFFF), shape = RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center,

                    ) {
                        val grid_icon = if (viewModel.Grid_value(i) != 0) "${viewModel.Grid_value(i)}" else ""
                        Text(
                            text = grid_icon,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(color = viewModel.Grid_color(i), shape = CircleShape)
                                .fillMaxSize(0.75f)
                                .padding(5.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            })

        Row(
            modifier = Modifier
                .fillMaxWidth(1.5f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = player1Name,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(
                        color = Color(0xFFFF5F57),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .width(150.dp)
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(60.dp))
            Text(
                text = "${viewModel.get_player_1_score()}",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .wrapContentWidth()
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(
                        color = Color(0xFFFF5F57),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .width(50.dp)
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )

        }
        Icon(
            Icons.Default.Clear,
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    viewModel.reset_game()
                }
                .clip(CircleShape)
                .size(25.dp)
                .background(color = Color(0x75FFFFFF), shape = CircleShape)
        )
    }

}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen(player1Name = "Khadeer", player2Name = "Madiha", viewModel = GameViewModel(), navController = rememberNavController())
}

