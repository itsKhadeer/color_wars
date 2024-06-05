package com.example.delta_task_1.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel: ViewModel() {

    private var blue = Color(0xFF2FB6F0)
    private var red = Color(0xFFFF5F57)
    var no_of_columns = 5
        private set
    private var arr = arrayListOf(blue, red)
    private var neutral = Color(0x00FFFFFF)



    var backgroundColor by mutableStateOf(arr[Random.nextInt(2)])
        private set
    var wins by mutableStateOf( false)
    var Player1_Arr by mutableStateOf( arrayListOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0))
    var Player2_Arr by mutableStateOf( arrayListOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0))
    companion object var turn = 0

    fun changeBackgroundColor() {
        
        if(backgroundColor == red) {
            backgroundColor = blue
        }
        else if(backgroundColor == blue) {
            backgroundColor = red
        }
    }


    fun Grid_color (index: Int): Color {
        return if(Player1_Arr[index] > 0) {
            red
        } else if(Player2_Arr[index] > 0) {
            blue
        } else {
            neutral
        }
    }
    fun Grid_value (index: Int): Int {
        return if(Player1_Arr[index] > 0) {
            Player1_Arr[index]
        } else if(Player2_Arr[index] > 0) {
            Player2_Arr[index]
        } else {
            0
        }
    }

    fun reset_game() {
        Player1_Arr = arrayListOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
        Player2_Arr = arrayListOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
        wins = false
        turn = 0
    }

    fun is_top_bottom_valid(top_bottom: Int):Boolean {
        return top_bottom in 0..24
    }
    fun is_left_right_valid(left_right: Int, index: Int): Boolean {
        val row_of_index = Math.floorDiv(index,no_of_columns)
        val row_of_left_right = Math.floorDiv(left_right,no_of_columns)

        return row_of_index == row_of_left_right
    }

    fun update_lists(Player1_Arr: ArrayList<Int>, Player2_Arr: ArrayList<Int>, index: Int) {
        if (check_win_condition() != "") {
            return
        }
        if(turn <= 2) {
            Player1_Arr[index] += 3
        } else {
            Player1_Arr[index]++
        }
        if(Player1_Arr[index] >= 4) {
            val top = index - no_of_columns
            val bottom = index + no_of_columns
            val left = index - 1
            val right = index + 1

            Player1_Arr[index] = 0

            if(is_top_bottom_valid(top)) {

                if(Player2_Arr[top] > 0) Player1_Arr[top] = Player2_Arr[top]
                Player2_Arr[top] = 0
                Player1_Arr[top]++
            }
            if(is_top_bottom_valid(bottom)) {
                if(Player2_Arr[bottom] > 0) Player1_Arr[bottom] = Player2_Arr[bottom]
                Player2_Arr[bottom] = 0
                Player1_Arr[bottom]++
            }
            if(is_left_right_valid(left, index)) {
                if(Player2_Arr[left] > 0) Player1_Arr[left] = Player2_Arr[left]
                Player2_Arr[left] = 0
                Player1_Arr[left]++
            }
            if(is_left_right_valid(right, index)) {
                if(Player2_Arr[right] > 0) Player1_Arr[right] = Player2_Arr[right]
                Player2_Arr[right] = 0
                Player1_Arr[right]++
            }
        }
    }
    fun check_win_condition(): String  {
        return if(Player2_Arr.all { i -> i == 0} && turn > 2) {

            wins = true;"Player1"

        }
        else if (Player1_Arr.all { i -> i == 0} && turn > 2) {
            wins = true;"Player2"
        }
        else {
            wins = false;""
        }

    }

    fun get_player_1_score(): Int {
        return Player1_Arr.sum()
    }
    fun get_player2_score(): Int {
        return Player2_Arr.sum()
    }
    fun on_click (index: Int) {
        Log.d("TAG", "before: ${turn} ")
        var i = index
        if (backgroundColor == blue && Grid_color(i) == neutral && Player2_Arr.all { it == 0 } && turn <= 2) {
            changeBackgroundColor()
            turn++;
            update_lists(Player2_Arr, Player1_Arr , index)
        }
        else if (backgroundColor == red && Grid_color(i) == neutral && Player1_Arr.all { it == 0 } && turn <= 2) {
            changeBackgroundColor()
            turn++;
            update_lists(Player1_Arr, Player2_Arr , index)
        }
        else if (backgroundColor == red && Grid_color(i) == red) {
            turn++;
            changeBackgroundColor()
            var should_do = false
            do {
                if(wins) break
                update_lists(Player1_Arr, Player2_Arr, i)
                check_win_condition()

                should_do = false
                for (x in Player1_Arr) {
                    if(x >= 4) {
                        i = Player1_Arr.indexOf(x)
                        should_do = true
                    }
                }
            } while (should_do)

        } else if(backgroundColor == blue && Grid_color(i) == blue){
            changeBackgroundColor()
            turn++;
            var should_do = false
            do {
                if(wins) break
                update_lists(Player2_Arr, Player1_Arr, i)
                check_win_condition()

                should_do = false
                for (x in Player2_Arr) {
                    if(x >= 4) {
                        i = Player2_Arr.indexOf(x)
                        should_do = true
                    }
                }
            } while (should_do)
        }
        Log.d("TAG", "after: ${turn} ")
    }
}