package com.example.samplecompose.ui

import androidx.navigation.NavHostController
import androidx.navigation.NavGraph.Companion.findStartDestination

/**
 * Sample destinations
 * ナビゲーションを使用するときに設定するIDの指定。
 * 渡来はナビゲーションエディターを使用して画面遷移を視覚的に操作していたが、
 * Composeではプログラム内で指定する必要があるようだ。(xmlを使用しないため)
 * @constructor Create empty Sample destinations
 */
object SampleDestinations {
    const val SAMPLE_1_ROUTE = "sample_1"
    const val SAMPLE_2_ROUTE = "sample_2"
}

class SampleNavigationActions(navController: NavHostController) {
    val sample1: () -> Unit = {
        /**
         * navigateないの引数に文字列を指定することによって、
         * ディスティネーションのIDを指定することができる。
         */
        navController.navigate(SampleDestinations.SAMPLE_1_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            /**
             * 下記は、公式にて。
             * 履歴を保存すべきではない同じターゲットのインスタンス(同様のデータ項目の詳細ページなど)
             * 間で横方向のナビゲーションを行う場合は、ナビゲーションターゲットをシングルトップとして起動します。
             *
             * 多分単方向での画面遷移を行うときに、指定されたIDをスタート地点として設定するかな？
             */
            launchSingleTop = true
            /**
             * 公式サイトにて
             * このナビゲーションアクションは、以前にsetPopUpToまたはpopUpToSaveState
             * 属性によって保存された状態を復元する必要があるかどうか。宛先IDがナビゲートされている状態で
             * 以前に状態が保存されていない場合、これは効果がありません。
             */
            restoreState = true
        }
    }
    val sample2: () -> Unit = {
        navController.navigate(SampleDestinations.SAMPLE_1_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }
}