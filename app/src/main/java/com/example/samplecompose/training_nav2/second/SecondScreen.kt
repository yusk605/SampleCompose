package com.example.samplecompose.training_nav2.second

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SecondScreen(transition:()->Unit){
    Surface{
        Row(
            modifier = Modifier.fillMaxSize().padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .height(80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = "Second ナビゲーションの学習")
                Spacer(modifier = Modifier.padding(4.dp))
                Button(onClick = transition) {
                    Text(text = "to Fires Screen")
                }
            }
        }
    }
}
