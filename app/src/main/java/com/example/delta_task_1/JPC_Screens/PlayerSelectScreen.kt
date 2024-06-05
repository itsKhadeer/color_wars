package com.example.delta_task_1.JPC_Screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.delta_task_1.R
import com.example.delta_task_1.Helpers.Screen
import com.example.delta_task_1.Helpers.clearFocusOnKeyboardDismiss

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerSelectScreen(navController: NavController){
    var player1Name by remember { mutableStateOf("") }
    var player2Name by remember { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxSize()
            .background(
                brush = Brush
                    .verticalGradient(
                        colors = listOf(
                            Color(0xFFFFC371),
                            Color(0xFFFF5F6D),
                        )
                    )
            )
            .padding(horizontal = 10.dp),
    ) {
        Box(contentAlignment = Alignment.Center,) {
            Image(painterResource(id = R.drawable.diamond_background), contentDescription = null)
            Text(text = "PLAYER INFORMATION", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight(0.5f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
            ) {
                PlayerName(0.39f, 1, Color(0xFFFF5F57))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(0.75f)
                        .background(
                            color = Color(0xFF3D4175),
                            shape = RoundedCornerShape(25.dp)
                        )
                ) {
                    Image(painterResource(id = R.drawable.red_player_img), contentDescription = null, modifier = Modifier
                        .size(45.dp)
                        .padding(top = 10.dp))
                    Row {
                        TextField(
                            value = player1Name,
                            onValueChange = {
                                player1Name = it
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(0x00AAAAAA),
                                focusedIndicatorColor = Color(0xFFFF5F57),
                                unfocusedIndicatorColor = Color(0xFFFF5F57),
                                disabledIndicatorColor = Color(0xFFFF5F57),
                                textColor = Color.White,
                                cursorColor = Color.White,
                            ),
                            textStyle = TextStyle(fontWeight = FontWeight(500), fontSize = 18.sp),
                            placeholder = {
                                Text(text = "enter name", color = Color(0x55FFFFFF))
                            },
                            singleLine = true,
                            modifier = Modifier
                                .clearFocusOnKeyboardDismiss()
                                .padding(bottom = 10.dp, start = 8.dp, end = 8.dp),
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth()
            ) {
                PlayerName(0.39f, 2, Color(0xFF2FB6F0))
                PlayerNameInput(0.75f, 2, Color(0xFF2FB6F0))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(0.75f)
                        .background(
                            color = Color(0xFF3D4175),
                            shape = RoundedCornerShape(25.dp)
                        )
                ) {
                    Image(painterResource(id = R.drawable.blue_player_img), contentDescription = null, modifier = Modifier
                        .size(45.dp)
                        .padding(top = 10.dp))
                    Row {
                        TextField(
                            value = player2Name,
                            onValueChange = {
                                player2Name = it
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(0x00AAAAAA),
                                focusedIndicatorColor = Color(0xFF2FB6F0),
                                unfocusedIndicatorColor = Color(0xFF2FB6F0),
                                disabledIndicatorColor = Color(0xFF2FB6F0),
                                textColor = Color.White,
                                cursorColor = Color.White,
                            ),
                            textStyle = TextStyle(fontWeight = FontWeight(500), fontSize = 18.sp),
                            placeholder = {
                                Text(text = "enter name", color = Color(0x55FFFFFF))
                            },
                            singleLine = true,
                            modifier = Modifier
                                .clearFocusOnKeyboardDismiss()
                                .padding(bottom = 10.dp, start = 8.dp, end = 8.dp),
                        )
                    }

                }

            }
        }
        Image(painterResource(id = R.drawable.red_blue_player_profile), contentDescription = null)
        Button(
            onClick = {
                val map = HashMap<String, String>()
                map["player1Name"] = player1Name
                map["player2Name"] = player2Name
                navController.navigate(Screen.GameScreen.withArgs(map))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2FB6F0)
            ),
            shape = RoundedCornerShape(50.dp),
        ) {
            Text(
                text = "START",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(500),
                    background = Color(0xFF2FB6F0),
                )
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerNameInput(fill_max_width: Float, player_number: Int, player_color: Color) {

}


@Composable
fun PlayerName(fill_max_width: Float, player_number: Int, player_color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(fill_max_width)
            .background(
                color = Color(0xFF3D4175),
                shape = RoundedCornerShape(25.dp)
            )
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(5.dp)
        )
        {
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(0.33f)
                    .background(
                        color = player_color
                    )

            ){}
            Text(
                text = player_number.toString(),
                fontSize = 50.sp,
                fontWeight = FontWeight(1000),
                color = player_color,
            )
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(0.66f)
                    .background(
                        color = player_color
                    )
            ){}
        }

        Text(
            text = "PLAYER",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight(1000),
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(0.9f)
                .background(
                    color = player_color,
                    shape = RoundedCornerShape(25.dp)
                ),

            textAlign = TextAlign.Center,
        )
    }

}

@Preview
@Composable
private fun DottedLinePreview() {
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .background(Color.White)
    ) {
        drawLine(
            color = Color.Black,
            start = Offset(10.dp.toPx(), 100.dp.toPx()),
            end = Offset(190.dp.toPx(), 100.dp.toPx()),
            strokeWidth = 5.dp.toPx(),
            cap = StrokeCap.Round, // important!
            pathEffect = PathEffect.dashPathEffect(
                intervals = floatArrayOf(
                    0f, // important!
                    8.dp.toPx(), // must be greater than stroke width
                ),
            ),
        )
    }
}