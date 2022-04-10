package com.example.samplecompose

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * Training app
 * 状態ホルダーを使用した、各状態を保存するためのクラス。
 * 状態を管理する
 * @property navController
 * @property scaffoldState
 * @property resources
 * @constructor Create empty Training app
 */
class TrainingApp(
    val navController: NavHostController,
    val scaffoldState: ScaffoldState,
    private val resources: Resources
) {

    /**
     * Companion
     * 公式サイトではクラスの外部にこのクラスの状態を保つインスタンスを生成しているが、
     * 思うに、静的メンバーで状態を生成するメンバーをこのクラス内で生成してもいいのでは
     * ないかと思ったので、コンパニオンオブジェクト内で宣言している。
     *
     * もしかしたら、関数として宣言しなければいけない理由があるのかもしれない。
     * @Composableの注釈のついた関数は、コンポーズの思想に基づくとクラスではなく
     * 関数として宣言する必要があるため。クラス内のメソッドにコンポーザブルの宣言を
     * しないのかもしれない。
     * @constructor Create empty Companion
     */
    companion object {
        @Composable
        fun rememberTrainingApp(
            navController: NavHostController = rememberNavController(),
            scaffoldState: ScaffoldState = rememberScaffoldState(),
            resources: Resources = LocalContext.current.resources
        ) = remember(navController,scaffoldState,resources) {
            mutableStateOf(
                TrainingApp(
                    navController, scaffoldState, resources
                )
            )
        }
    }
}