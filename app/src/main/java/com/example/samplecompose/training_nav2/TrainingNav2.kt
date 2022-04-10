package com.example.samplecompose.training_nav2

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable

@Composable
fun NavTrainingNav2(){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { {} },
        drawerContent = {}
    ) {

    }
}