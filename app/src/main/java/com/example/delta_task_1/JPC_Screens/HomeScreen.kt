package com.example.delta_task_1.JPC_Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.delta_task_1.Helpers.PreferencesManager
import com.example.delta_task_1.R
import com.example.delta_task_1.Helpers.Screen

@Composable
fun HomeScreen(navController: NavController) {
    var openDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current;
    val preferencesManager = remember { PreferencesManager(context) }
    val red_wins = preferencesManager.getData("red_wins", "0");
    val blue_wins = preferencesManager.getData("blue_wins", "0")
    if(openDialog) CustomDialog(setShowDialog = {openDialog = it})
    Column (
        modifier = Modifier.background(
            brush = Brush
                .verticalGradient(
                    colors = listOf(
                        Color(0xFFFFC371),
                        Color(0xFFFF5F6D),
                    )
                )
        )
    ){
//        val wins_color = if (red_wins > blue_wins) Color(0xFFFF5F57) else if(blue_wins > red_wins) Color(0xFF2FB6F0) else Color.Black
//        Text(text = "Red wins   :   $red_wins\n" +
//                "Blue wins  :   $blue_wins",
//            color = Color.White,
//            modifier = Modifier
//                .padding(10.dp)
//                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
//                .wrapContentSize()
//                .background(
//                    color = wins_color,
//                    shape = RoundedCornerShape(8.dp)
//                )
//                .padding(10.dp)
//            ,
//            textAlign = TextAlign.Start)
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {



            Text(
                text = "COLOR CONQUEST",
                style = TextStyle(
                    brush = Brush
                        .verticalGradient(
                            colors = listOf(Color.Black,
                                Color(0xFFE48C86)
                            )
                        ),
                    fontSize = 60.sp,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight(1000),
                )
            )


            Image(painterResource(id = R.drawable.red_blue_player_profile), contentDescription = "red blue player proile img")

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            )
            {
                Button(
                    onClick = {
                        navController.navigate(Screen.PlayerSelectScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2FB6F0)
                    ),
                    shape = RoundedCornerShape(50.dp),
                ) {
                    Text(
                        text = "PLAY",
                        style = TextStyle(
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight(500),
                            background = Color(0xFF2FB6F0),
                        )
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(onClick = {
                    openDialog = true
                },
                    modifier= Modifier.size(30.dp),
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),

                    ) {
                    Image(painterResource(id = R.drawable.baseline_question_mark_24), contentDescription = null, Modifier.size(20.dp))
                }
            }

        }

    }

}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
