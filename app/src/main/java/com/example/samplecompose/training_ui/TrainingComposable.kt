package com.example.samplecompose.training_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.samplecompose.R

@Composable
fun Training1() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Page title", maxLines = 2)
                },
                navigationIcon = {

                }
            )
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_android_black_24dp),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
                    )
                }
            }
        }
    ) {
        Row(
            Modifier
                .clickable(onClick = { /* Ignoring onClick */ })
                .padding(8.dp)
        ) {
            //このUIをかますことでダークモードにしたときに文字が黒で潰れてまうのを防いだりできる
            Surface(
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                Image(
                    //リソースを指定する箇所
                    painter = painterResource(id = R.drawable.ic_android_black_24dp),
                    //この画像の説明を指定するようだ
                    contentDescription = "icon",
                    //画像の大きさなどの設定
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)//丸い形で切り取りができる
                        .border(width = 1.dp, color = Color.Cyan, CircleShape)
                )
            }
            //スペースの幅
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "アンドロイド",
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(text = "sample")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TrainingPreview() {
    Training1()
}