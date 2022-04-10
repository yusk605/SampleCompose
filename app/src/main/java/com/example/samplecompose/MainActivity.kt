package com.example.samplecompose

import androidx.compose.foundation.lazy.items
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.samplecompose.ui.theme.Purple200
import com.example.samplecompose.utils.rememberWindowSizeClass
import kotlinx.android.parcel.Parcelize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = rememberWindowSizeClass()
            com.example
                .samplecompose
                .training_nav
                .NavTrainingApp(windowSizeClass)
//            var count by remember { mutableStateOf(0) }
//            ClickCounter(count) { count++ }
        }
    }
}


@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    Column() {
        Button(onClick = onClick) {
            Text("I've been clicked $clicks times")
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = "counter$clicks")
        Spacer(modifier = Modifier.padding(4.dp))
        if (clicks>10) {
            Text(text = "再コンポーズされません")
        }
    }
}

@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("aaa") }
        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    }
}


/**
 * City
 * シリアライズしたデータのサンプルクラスとして、
 * 状態の保存の学習用として作成
 * @property name
 * @property country
 * @constructor Create empty City
 */
@Parcelize
data class City(val name: String, val country: String) : Parcelable

/**
 * City saver
 * 渡されたデータがシリアライズできない場合の初期状態を確保するためのセーバー
 */
val CitySaver = run {
    val nameKey = "Name"
    val countryKey = "Country"
    mapSaver(
        save = { mapOf(nameKey to it.name, countryKey to it.country) },
        restore = { City(it[nameKey] as String, it[countryKey] as String) }
    )
}

@Composable
fun CityScreen() {
    var selectedCity = rememberSaveable(stateSaver = CitySaver) {
        mutableStateOf(City("Madrid", "Spain"))
    }
}

/**
 * ■Sample card
 * 最下層のレイヤー を表現したコンポネート
 * リストを表示させる際に、表示する１行分のUIを表示するための関数
 * 注意としては、この関数内で繰り返し分よ使用してUIを複数生成することは、
 * 出来ない。もしリストのように複数のUIを表示させる際には、LazyColumn を使うこと。
 * @param name
 */
@Composable
fun SampleCard(name: String, onDelete: (String) -> Unit) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_android_black_24dp),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = "Hello Component $name!",
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            //テキストとテキストの間のこと
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$name",
                style = MaterialTheme.typography.body2
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Button(onClick = { onDelete(name) }) {
                Text(text = "DELETE")
            }
        }
    }
}

/**
 * ■ SampleCardList　
 * ・SampleCard の関数で生成されたUIを使用してリストの表示を行いたい時に使用する関数
 * @param messages
 */
@Composable
fun SampleCardList(messages: List<String>, onDelete: (String) -> Unit) {
    Column {
        LazyColumn {
            items(messages) { message -> SampleCard(message, onDelete) }
        }
    }
}

@Composable
fun SampleButton(
    onAddData: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        Button(
            onClick = onAddData,
            colors = ButtonDefaults.textButtonColors(backgroundColor = Purple200)
        ) { Text(text = "Add") }

    }
}

/**
 * ■ Sample screen
 * 上位レイヤーのコンポネート。
 * 状態を保持しておき、データの状態が変更された時に再コンポーズする
 * AddButtonでリストを追加しているのだがコンポーズ関数内では、副作用
 * が起きないため新しいリストとデータを生成してそのデータを再代入する
 * 必要があるみたいだ。
 */
@Composable
fun SampleScreen() {
    val list = mutableListOf("1", "2", "3")

    var data by rememberSaveable { mutableStateOf(list) }
    var count by rememberSaveable { mutableStateOf(3) }
    SampleCardList(messages = data) {
        val newList = mutableListOf<String>()
        newList.addAll(data)
        newList.remove(it)
        data = newList
    }
    SampleButton {//この処理ブロック内では副作用が発生しないため
        //ここで新たにリストを生成し
        val newList = mutableListOf<String>()
        //元のデータ全てを詰め込み直してから
        newList.addAll(data)
        //新しいデータを追加する
        newList.add("${++count}")
        data = newList
    }
}

@Preview(showSystemUi = true)       //全体の表示を確認できる。
//@Preview(showBackground = true)   //←UIの確認のみ
@Composable
fun DefaultPreview() {
}