package com.example.samplecompose.training_nav.first

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FirstScreen(transition:()->Unit){
    Surface{
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
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
                Text(text = "First ナビゲーションの学習")
                Spacer(modifier = Modifier.padding(4.dp))
                Button(onClick = transition) {
                    Text(text = "to Second Screen")
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
    }
}




@Composable
@Preview(showSystemUi = true)
fun FirstScreenPreview(){
    com.example.samplecompose.training_nav2.first.FirstScreen { }
}