package com.example.samplecompose.ui

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.samplecompose.ui.theme.SampleComposeTheme
import com.example.samplecompose.utils.WindowSize
import com.google.accompanist.insets.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun SampleApp(
    windowSize: WindowSize
) {
    SampleComposeTheme {
        ProvideWindowInsets {
            /**
             * Nav controller
             * ナビゲーションを行う準備として
             * ナビコントローラの状態を生成、この際に既に自前で作成したナビゲーションを作る
             * ナビゲーションを制御するオブジェクト
             * systemUiController よくわからないがあたらに便利なオブジェクトがあるっぽい
             */
            val navController = rememberNavController()
            val navigationActions =
                remember(navController) { SampleNavigationActions(navController) }
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val systemUiController = rememberSystemUiController()
            val currentRoute =
                navBackStackEntry?.destination?.route ?: SampleDestinations.SAMPLE_1_ROUTE

            /**
             * Coroutine scope
             * コルーチンで非同期処理をを実行する際に必要なもの
             */
            val coroutineScope = rememberCoroutineScope()

            val isExpandedScreen = windowSize == WindowSize.Expanded
            val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)


            val darkIcons = MaterialTheme.colors.isLight

            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
            }
            ModalDrawer(
                drawerContent = {
                    AppDrawer(
                        currentRoute = currentRoute,
                        navigateToSample1 = navigationActions.sample1,
                        navigateToSample2 = navigationActions.sample2,
                        closeDrawer = { coroutineScope.launch { sizeAwareDrawerState.close() } },
                        modifier = Modifier
                            .statusBarsPadding()
                            .navigationBarsPadding()
                    )
                },
                drawerState = sizeAwareDrawerState,
                // Only enable opening the drawer via gestures if the screen is not expanded
                gesturesEnabled = !isExpandedScreen
            ) {
                SampleNavGraph(
                    navController
                    )
            }
        }
    }
}

/**
 * Determine the drawer state to pass to the modal drawer.
 */
@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        DrawerState(DrawerValue.Closed)
    }
}

/**
 * Determine the content padding to apply to the different screens of the app
 */
@Composable
fun rememberContentPaddingForScreen(additionalTop: Dp = 0.dp) =
    rememberInsetsPaddingValues(
        insets = LocalWindowInsets.current.systemBars,
        applyTop = false,
        applyEnd = false,
        applyStart = false,
        additionalTop = additionalTop
    )