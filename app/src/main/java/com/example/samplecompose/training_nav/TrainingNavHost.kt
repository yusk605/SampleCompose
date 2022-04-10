package com.example.samplecompose.training_nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.samplecompose.training_nav2.first.FirstScreen
import com.example.samplecompose.training_nav2.second.SecondScreen


@Composable
fun TrainingNavHost(navController:NavHostController, modifier: Modifier){
    val startDestination = TrainingScreen.First.name
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable(TrainingScreen.First.name){
            FirstScreen { navController.navigate(TrainingScreen.Second.name) }
        }
        composable(TrainingScreen.Second.name){
            SecondScreen { navController.navigate(TrainingScreen.First.name) }
        }
    }
}