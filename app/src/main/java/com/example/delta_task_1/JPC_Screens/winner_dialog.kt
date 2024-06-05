package com.example.delta_task_1.JPC_Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.delta_task_1.Helpers.PreferencesManager
import com.example.delta_task_1.Helpers.Screen
import com.example.delta_task_1.R
import com.example.delta_task_1.ViewModels.GameViewModel

@Composable
fun Winner_dialog(setShowDialog: (Boolean) -> Unit, winner: String, winner_color: Color, viewModel: GameViewModel, navController: NavController) {

    

    Dialog(
        onDismissRequest = { setShowDialog(false) },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFF3D4175)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = winner,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                            .fillMaxWidth()
                            .padding(5.dp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.33f)
                                .height(2.dp)
                                .background(color = winner_color, shape = RoundedCornerShape(20.dp))

                        ){}
                        Image(painterResource(id = R.drawable.medal_png), contentDescription = null, modifier= Modifier.size(50.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.66f)
                                .height(2.dp)
                                .background(color = winner_color, shape = RoundedCornerShape(20.dp))

                        ){}

                    }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = "WINS!",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(500),
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        textAlign = TextAlign.Center)

                    Button(
                        onClick = {
                            viewModel.reset_game()
                            setShowDialog(false)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2FB6F0)
                        )

                    ) {
                        Text(
                            text = "Play Again",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center

                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            viewModel.reset_game()
                            navController.navigate(Screen.HomeScreen.route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF5F57)
                        )
                    ) {
                        Text(
                            text = "Home",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

    }
}
@Preview
@Composable
fun winner_dialog_Preview() {
    var openDialog by remember { mutableStateOf(true) }

    Winner_dialog(setShowDialog ={openDialog = it} , winner = "Khadeer", winner_color = Color(0xFFFF5F57), viewModel = GameViewModel(), navController = rememberNavController() )
}