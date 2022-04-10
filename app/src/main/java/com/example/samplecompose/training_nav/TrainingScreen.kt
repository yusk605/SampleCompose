package com.example.samplecompose.training_nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Training screen
 * コンポネートナビゲーションを使用する際に、ナビゲーションホストに
 * ディスティネーションを指定するIDの文字列を列挙したクラスで遷移できる
 * 画面の数だけ列挙すること。列挙されたオブジェクトのプロパティに、icon
 * ImageVectorが指定されているが、topBarに遷移できる画面の数だけ、iconを
 * 表示させるため。
 *
 * @property icon
 * @constructor Create empty Training screen
 */
enum class TrainingScreen(val icon:ImageVector) {
    First(Icons.Filled.Star),
    Second(Icons.Filled.Face);
    companion object{
        /**
         * From route
         * NavControllerから、画面の状態が変更された時に変更のあったルートの
         * idをもとに列挙型のオブジェクトを返すためのスタディックメソッド
         * NavTrainingApp のコンポーズされた関数を参考にすること。
         * @param route
         * @return
         */
        fun fromRoute(route: String?): TrainingScreen =
            when (route?.substringBefore("/")) {
                First.name -> First
                Second.name -> Second
                null -> First
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}