package com.example.samplecompose.training_nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.samplecompose.training_ui.TrainingDrawer
import com.example.samplecompose.utils.WindowSize
import kotlinx.coroutines.launch

@Composable
fun NavTrainingApp(
    windowSize: WindowSize
) {
    val isExpandedScreen = windowSize == WindowSize.Expanded
    val nabController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val allScreen = TrainingScreen.values().toList()
    val backStackEntry = nabController.currentBackStackEntryAsState()
    val currentScreen = TrainingScreen.fromRoute(backStackEntry.value?.destination?.route)
    val scaffoldState = rememberScaffoldState()
    val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            com.example.samplecompose.training_nav2.overview.TrainingTabRow(
                allScreen = allScreen,
                onTabSelected = { screen -> nabController.navigate(screen.name) },
                screen = currentScreen,
                openDrawer = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerContent = {
            TrainingDrawer(
            currentRoute = "",
            navigateToFirst = { /*TODO*/ },
            navigateToSecond = { /*TODO*/ },
            closeDrawer = { /*TODO*/ }
            )
        }
    ) { innerPadding ->
        TrainingNavHost(
            navController = nabController,
            Modifier.padding(innerPadding)
        )
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
