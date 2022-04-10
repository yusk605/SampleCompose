package com.example.samplecompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun SampleNavGraph(
    navController: NavHostController = rememberNavController(),
    destinationId:String=SampleDestinations.SAMPLE_1_ROUTE ,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit = {}
){
    NavHost(
        navController = navController,
        startDestination = destinationId,
        modifier = modifier
    ){
        composable(SampleDestinations.SAMPLE_1_ROUTE){
            TODO("ViewMode のインスタンス生成をこの中で行う")
            TODO("画面の表示を行うためのComposable関数をここで呼び出すこと")
        }
        composable(SampleDestinations.SAMPLE_2_ROUTE){

        }
    }
}