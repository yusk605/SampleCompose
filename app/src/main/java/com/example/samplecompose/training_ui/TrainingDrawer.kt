package com.example.samplecompose.training_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.samplecompose.R

@Composable
fun TrainingDrawer(
    currentRoute: String,
    navigateToFirst: () -> Unit,
    navigateToSecond: () -> Unit,
    modifier: Modifier = Modifier,
    closeDrawer: () -> Unit
 ){
    Column(
        modifier
            .fillMaxSize()

    ) {
        Image(
            painter = painterResource(R.drawable.ic_android_black_24dp),
            contentDescription = ""
        )
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        Button(onClick = navigateToFirst) { Text(text = "text1") }
        Button(onClick = navigateToFirst) { Text(text = "text2") }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewDrawer() {
    TrainingDrawer(
        currentRoute = "",
        navigateToFirst = { /*TODO*/ },
        navigateToSecond = { /*TODO*/ },
        closeDrawer = { /*TODO*/ }
    )
}
